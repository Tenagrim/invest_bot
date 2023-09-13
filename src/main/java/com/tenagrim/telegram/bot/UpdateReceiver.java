package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.mappers.MessageMapper;
import com.tenagrim.telegram.model.BotConfig;
import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.Command;
import com.tenagrim.telegram.model.TGUser;
import com.tenagrim.telegram.repository.ChapterRepository;
import com.tenagrim.telegram.repository.CommandRepository;
import com.tenagrim.telegram.service.TGUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateReceiver {
    private final CommandRepository commandRepository;
    private final MessageMapper sendMessageMapper;
    private final ChapterRepository chapterRepository;
    private final TGUserService tgUserService;

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update, BotConfig botConfig) {
        // try-catch, чтобы при несуществующей команде просто возвращать пустой список
        try {
            // Проверяем, если Update - сообщение с текстом
            if (isMessageWithContact(update)){
                final Message message = update.getMessage();
                final Long chatId = message.getFrom().getId();
                tgUserService.addContact(message.getFrom(),message.getContact());
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(chatId.toString());
                sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
                sendMessage.setText("Спасибо, ваш номер отправлен менеджеру");
                return List.of(sendMessage);
            } else if (isMessageWithText(update)) {
                // Получаем Message из Update
                final Message message = update.getMessage();
                // Получаем айди чата с пользователем
                final Long chatId = message.getFrom().getId();
                TGUser user = tgUserService.saveIfNotSaved(message.getFrom());

                Command command = commandRepository.findByText(message.getText())
                        .orElseThrow(UnsupportedOperationException::new);
                Chapter chapter = chapterRepository.findByItemIdAndDataVersionId(command.getChapterId(), botConfig.getCurrentVersion().getId())
                        .orElseThrow(UnsupportedOperationException::new);
                return List.of(sendMessageMapper.mapSend(chapter, chatId.toString()));

            } else if (update.hasCallbackQuery()) {
                List<PartialBotApiMethod<? extends Serializable>> result = new ArrayList<>();
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                Long chapterId =  Long.parseLong(callbackQuery.getData()); // TODO validation
                final Long chatId = callbackQuery.getFrom().getId();
                Chapter chapter = chapterRepository.findByItemIdAndDataVersionId(chapterId, botConfig.getCurrentVersion().getId())
                        .orElseThrow(UnsupportedOperationException::new);
                if (chapter.getItemId() == 4L){ // ToDO: switch to typed actions
                    SendMessage sendMessage = new SendMessage();
                    sendMessage.setText(chapter.getText());
                    KeyboardButton button = new KeyboardButton();
                    button.setRequestContact(true);
                    button.setText("Отравить контакт");
                    ReplyKeyboard replyKeyboard = new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(button))));
                    sendMessage.setReplyMarkup(replyKeyboard);
                    sendMessage.setChatId(chatId.toString());
                    result.add(sendMessage);
                }else{
                    EditMessageText message = sendMessageMapper.mapEdit(chapter, chatId.toString());
                    message.setMessageId(callbackQuery.getMessage().getMessageId());

                    result.add(message);
                }

                return result;
            }
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }

    private boolean isMessageWithContact(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasContact();
    }

}
