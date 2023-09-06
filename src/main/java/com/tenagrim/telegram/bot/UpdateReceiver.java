package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.bot.handler.Handler;
import com.tenagrim.telegram.mappers.MessageMapper;
import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.Command;
import com.tenagrim.telegram.repository.ChapterRepository;
import com.tenagrim.telegram.repository.CommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateReceiver {
    private final CommandRepository commandRepository;
    private final MessageMapper sendMessageMapper;
    private final ChapterRepository chapterRepository;

    public List<PartialBotApiMethod<? extends Serializable>> handle(Update update) {
        // try-catch, чтобы при несуществующей команде просто возвращать пустой список
        try {
            // Проверяем, если Update - сообщение с текстом
            if (isMessageWithText(update)) {
                // Получаем Message из Update
                final Message message = update.getMessage();
                // Получаем айди чата с пользователем
                final Long chatId = message.getFrom().getId();

                Command command = commandRepository.findByText(message.getText())
                        .orElseThrow(UnsupportedOperationException::new);
                return List.of(sendMessageMapper.mapSend(command.getChapter(), chatId.toString()));

            } else if (update.hasCallbackQuery()) {
                final CallbackQuery callbackQuery = update.getCallbackQuery();
                Long chapterId =  Long.parseLong(callbackQuery.getData()); // TODO validation
                final Long chatId = callbackQuery.getFrom().getId();
                Chapter chapter = chapterRepository.findById(chapterId)
                        .orElseThrow(UnsupportedOperationException::new);
                EditMessageText message = sendMessageMapper.mapEdit(chapter, chatId.toString());
                message.setMessageId(callbackQuery.getMessage().getMessageId());

                return List.of(message);
            }
            throw new UnsupportedOperationException();
        } catch (UnsupportedOperationException e) {
            return Collections.emptyList();
        }
    }

    private boolean isMessageWithText(Update update) {
        return !update.hasCallbackQuery() && update.hasMessage() && update.getMessage().hasText();
    }

}
