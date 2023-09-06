package com.tenagrim.telegram.mappers;

import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.ChapterButton;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    public SendMessage mapSend(Chapter chapter, String chatId){
        SendMessage result = new SendMessage();
        result.setText(chapter.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(chapter.getChapterButtons()));
        return result;
    }

    public EditMessageText mapEdit(Chapter chapter, String chatId){
        EditMessageText result = new EditMessageText();
        result.setText(chapter.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(chapter.getChapterButtons()));
        return result;
    }

    private InlineKeyboardMarkup mapInlineKeyboard(Set<ChapterButton> chapterButtons){
        List<List<InlineKeyboardButton>> buttons = chapterButtons.stream()
                .sorted(Comparator.comparing(ChapterButton::getPlacement))
                .map(a->List.of(mapInlineButton(a)))
                .collect(Collectors.toList());
        InlineKeyboardMarkup result = new InlineKeyboardMarkup(buttons); // todo intelligent placement
        return result;
    }

    private InlineKeyboardButton mapInlineButton(ChapterButton action){
        InlineKeyboardButton result = new InlineKeyboardButton();
        result.setText(action.getText());
        result.setCallbackData(action.getTargetChapterId().toString());
        return result;
    }

}
