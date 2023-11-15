package com.tenagrim.telegram.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenagrim.telegram.dto.CallbackQueryData;
import com.tenagrim.telegram.model.chapter.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.File;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${bot.attachements-path}")
    private String attachmentsFolder;

    public PartialBotApiMethod<? extends Serializable> mapAttachment(Attachment attachment, String chatId) {
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        InputFile inputFile = new InputFile(new File(attachmentsFolder + "/" +attachment.getFilename()));
        sendDocument.setDocument(inputFile);
        return sendDocument;
    }

    public SendMessage mapSend(Paragraph paragraph, String chatId) {
        SendMessage result = new SendMessage();
        result.setText(paragraph.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(paragraph.getParagraphButtons()));
        result.setParseMode("markdown");
        return result;
    }

    public EditMessageText mapEdit(Paragraph paragraph, String chatId) {
        EditMessageText result = new EditMessageText();
        result.setText(paragraph.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(paragraph.getParagraphButtons()));
        result.setParseMode("markdown");
        return result;
    }

    private InlineKeyboardMarkup mapInlineKeyboard(Set<ParagraphButton> chapterButtons) {
        List<List<InlineKeyboardButton>> buttons = chapterButtons.stream()
                .collect(Collectors.groupingBy(ParagraphButton::getPlacement))
                .entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .map(a -> mapInlineButton(a.getValue()))
                .collect(Collectors.toList());
        InlineKeyboardMarkup result = new InlineKeyboardMarkup(buttons); // todo intelligent placement
        return result;
    }

    private List<InlineKeyboardButton> mapInlineButton(List<ParagraphButton> action) {
        return action.stream().map(this::mapInlineButton).collect(Collectors.toList());
    }

    private InlineKeyboardButton mapInlineButton(ParagraphButton action) {
        InlineKeyboardButton result = new InlineKeyboardButton();
        result.setText(action.getText());
        Long targetChapterId = action.getTargetChapterId() != null ? action.getTargetChapterId() : action.getParagraph().getChapter().getItemId();
//        result.setCallbackData(String.valueOf(targetChapterId));
        try {
            result.setCallbackData(objectMapper.writeValueAsString(CallbackQueryData.builder()
                    .id(targetChapterId)
                    .from(action.getParagraph().getId())
                    .build()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    public List<PartialBotApiMethod<? extends Serializable>> map(Chapter chapter, CallbackQuery callbackQuery) {
        return map(chapter, callbackQuery, Collections.emptyMap());
    }

    public List<PartialBotApiMethod<? extends Serializable>> map(Chapter chapter, CallbackQuery callbackQuery, Map<String, Object> mapProperties) {
        AtomicBoolean firstElement = new AtomicBoolean(false);
        String chatId = callbackQuery.getFrom().getId().toString();
        return Stream.concat(chapter.getAttachments()
                .stream().map(a -> mapAttachment(a, chatId)),

                chapter.getChapterParagraphs().stream()
                .sorted((a, b) -> b.getPlacement() - a.getPlacement())
                .map(p -> {
                    if (!mapProperties.get("chapter_sequence").equals("true") && // TODO sysname to enum, make values typed
                            !firstElement.get()) {
                        firstElement.set(true);
                        EditMessageText edit = mapEdit(p, chatId);
                        edit.setMessageId(callbackQuery.getMessage().getMessageId());
                        return edit;
                    }
                    return mapSend(p, chatId);
                })).collect(Collectors.toList());
    }

    public List<PartialBotApiMethod<? extends Serializable>> map(Chapter chapter, String chatId) {
        return Stream.concat(chapter.getAttachments()
                        .stream().map(a -> mapAttachment(a, chatId)),
                chapter.getChapterParagraphs().stream()
                        .sorted((a, b) -> b.getPlacement() - a.getPlacement())
                        .map(p -> mapSend(p, chatId))
        ).collect(Collectors.toList());
    }

}
