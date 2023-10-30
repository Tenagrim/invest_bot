package com.tenagrim.telegram.mappers;

import com.tenagrim.telegram.model.chapter.Chapter;
import com.tenagrim.telegram.model.chapter.ChapterButton;
import com.tenagrim.telegram.model.chapter.Paragraph;
import com.tenagrim.telegram.model.chapter.ParagraphButton;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    public SendMessage mapSend(Paragraph paragraph, String chatId){
        SendMessage result = new SendMessage();
        result.setText(paragraph.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(paragraph.getParagraphButtons()));
        result.setParseMode("markdown");
        return result;
    }

    public EditMessageText mapEdit(Paragraph paragraph, String chatId){
        EditMessageText result = new EditMessageText();
        result.setText(paragraph.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(paragraph.getParagraphButtons()));
        result.setParseMode("markdown");
        return result;
    }

    private InlineKeyboardMarkup mapInlineKeyboard(Set<ParagraphButton> chapterButtons){
        List<List<InlineKeyboardButton>> buttons = chapterButtons.stream()
                .collect(Collectors.groupingBy(ParagraphButton::getPlacement))
                .entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .map(a->mapInlineButton(a.getValue()))
                .collect(Collectors.toList());
        InlineKeyboardMarkup result = new InlineKeyboardMarkup(buttons); // todo intelligent placement
        return result;
    }

    private List<InlineKeyboardButton> mapInlineButton(List<ParagraphButton> action){
        return action.stream().map(this::mapInlineButton).collect(Collectors.toList());
    }

    private InlineKeyboardButton mapInlineButton(ParagraphButton action){
        InlineKeyboardButton result = new InlineKeyboardButton();
        result.setText(action.getText());
        Long targetChapterId = action.getTargetChapterId() != null? action.getTargetChapterId() : action.getParagraph().getChapter().getItemId();
        result.setCallbackData(String.valueOf(targetChapterId));
        return result;
    }

    public List<BotApiMethod<? extends Serializable>> map(Chapter chapter, CallbackQuery callbackQuery){
        return map(chapter, callbackQuery, Collections.emptyMap());
    }

    public List<BotApiMethod<? extends Serializable>> map(Chapter chapter, CallbackQuery callbackQuery, Map<String, Object> mapProperties){
        AtomicBoolean firstElement = new AtomicBoolean(false);
        String chatId = callbackQuery.getFrom().getId().toString();
        return  chapter.getChapterParagraphs().stream()
                .sorted((a,b)->b.getPlacement()-a.getPlacement())
                .map(p->{
            if(!mapProperties.get("chapter_sequence").equals("true") && // TODO sysname to enum, make values typed
                    !firstElement.get()){
                firstElement.set(true);
                EditMessageText edit = mapEdit(p, chatId);
                edit.setMessageId(callbackQuery.getMessage().getMessageId());
                return edit;
            }
            return mapSend(p, chatId);
        }).collect(Collectors.toList());
    }

    public List<BotApiMethod<? extends Serializable>> map(Chapter chapter, String chatId){
        return  chapter.getChapterParagraphs().stream()
                .sorted((a,b)->b.getPlacement()-a.getPlacement())
                .map(p->{
                    return mapSend(p, chatId);
                }).collect(Collectors.toList());
    }

}
