package com.tenagrim.telegram.bot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenagrim.telegram.dto.CallbackQueryData;
import com.tenagrim.telegram.mappers.MessageMapper;
import com.tenagrim.telegram.model.config.BotConfig;
import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.model.Command;
import com.tenagrim.telegram.model.TGUser;
import com.tenagrim.telegram.repository.ChapterRepository;
import com.tenagrim.telegram.repository.CommandRepository;
import com.tenagrim.telegram.service.TGUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.MessageEntity;
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
    private final IntegrationService integrationService;
    private final ObjectMapper objectMapper;

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update, BotConfig botConfig) {
        // try-catch, чтобы при несуществующей команде просто возвращать пустой список
        try {
            // Проверяем, если Update - сообщение с текстом
            if (isMessageWithContact(update)){
                final Message message = update.getMessage();
                final Long chatId = message.getFrom().getId();
                tgUserService.addContact(message.getFrom(), message.getContact());
                integrationService.sendContact(botConfig,message.getFrom(), message.getContact());
                return List.of(getContactReply(chatId.toString()));
            } else if (isMessageWithText(update)) {
                // Получаем Message из Update
                final Message message = update.getMessage();
                // Получаем айди чата с пользователем
                final Long chatId = message.getFrom().getId();

                if (message.isCommand()) {

                    MessageEntity botCommand = message.getEntities().stream()
                            .filter(ent->ent.getType().equals("bot_command"))
                            .findFirst().orElseThrow(UnsupportedOperationException::new);

                    String commandArg = getCommandArg(botCommand.getText(), message.getText());

                    TGUser user = tgUserService.saveIfNotSaved(message.getFrom(), commandArg);

                    Command command = commandRepository.findByText(botCommand.getText())
                            .orElseThrow(UnsupportedOperationException::new);
                    Chapter chapter = chapterRepository.findByItemIdAndDataVersionId(command.getChapterId(), botConfig.getCurrentVersion().getId())
                            .orElseThrow(UnsupportedOperationException::new);
                    return sendMessageMapper.map(chapter, chatId.toString());
                }

            } else if (update.hasCallbackQuery()) {
                List<PartialBotApiMethod<? extends Serializable>> result = new ArrayList<>();
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                CallbackQueryData callbackQueryData = objectMapper.readValue(callbackQuery.getData(), CallbackQueryData.class);
                Long chapterId =  callbackQueryData.getId(); // TODO validation
                final Long chatId = callbackQuery.getFrom().getId();
                Chapter chapter = chapterRepository.findByItemIdAndDataVersionId(chapterId, botConfig.getCurrentVersion().getId())
                        .orElseThrow(UnsupportedOperationException::new);
                if (!chapter.getIntegrationTriggers().isEmpty()){
                    integrationService.pushRecords(botConfig, chapter, chatId, callbackQueryData);
                }
                if (chapter.getItemId() == 4L){ // ToDO: switch to typed actions
                    List<PartialBotApiMethod<? extends Serializable>> to_send = sendMessageMapper.map(chapter, chatId.toString());
                    SendMessage sendMessage = (SendMessage) to_send.get(0);
                    KeyboardButton button = new KeyboardButton();
                    button.setRequestContact(true);
                    button.setText("Отравить контакт");
                    ReplyKeyboard replyKeyboard = new ReplyKeyboardMarkup(List.of(new KeyboardRow(List.of(button))));
                    sendMessage.setReplyMarkup(replyKeyboard);
                    sendMessage.setChatId(chatId.toString());
                    result.add(sendMessage);
                } else{
                    result.addAll(sendMessageMapper.map(chapter, callbackQuery, botConfig.getBotConfigProperties()));
                }

                return result;
            }
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }

    private boolean isMessageWithContact(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasContact();
    }


    private SendMessage getContactReply(String chatId){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(new ReplyKeyboardRemove(true));
        sendMessage.setText("Спасибо за ваш контакт! В ближайшее время с Вами свяжется один из наших [менеджеров](https://gi-agency.ru/tg-bot-thanks) \uD83D\uDC48");
        sendMessage.setParseMode("markdown");
        return sendMessage;
    }

    private String getCommandArg(String command, String message){
        if (command.length() != message.length()){
            String result = message.substring(command.length() + 1);
            return  (result.length() > 255) ? null : result; // TODO move limit to constants
        }
        return null;
    }

}
