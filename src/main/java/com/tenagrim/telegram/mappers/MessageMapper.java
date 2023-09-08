package com.tenagrim.telegram.mappers;

import com.tenagrim.telegram.model.Chapter;
import com.tenagrim.telegram.model.ChapterButton;
import org.mapstruct.Mapper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {
    public SendMessage mapSend(Chapter chapter, String chatId){
        SendMessage result = new SendMessage();
        result.setText(chapter.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(chapter.getChapterButtons()));
        result.setParseMode("markdown");
        return result;
    }

    public EditMessageText mapEdit(Chapter chapter, String chatId){
        EditMessageText result = new EditMessageText();
        result.setText(chapter.getText());
        result.setChatId(chatId);
        result.setReplyMarkup(mapInlineKeyboard(chapter.getChapterButtons()));
        result.setParseMode("markdown");
        return result;
    }

    private InlineKeyboardMarkup mapInlineKeyboard(Set<ChapterButton> chapterButtons){
        List<List<InlineKeyboardButton>> buttons = chapterButtons.stream()
                .collect(Collectors.groupingBy(ChapterButton::getPlacement))
                .entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
                .map(a->mapInlineButton(a.getValue()))
                .collect(Collectors.toList());
        InlineKeyboardMarkup result = new InlineKeyboardMarkup(buttons); // todo intelligent placement
        return result;
    }

    private List<InlineKeyboardButton> mapInlineButton(List<ChapterButton> action){
        return action.stream().map(this::mapInlineButton).collect(Collectors.toList());
    }
    private InlineKeyboardButton mapInlineButton(ChapterButton action){
        InlineKeyboardButton result = new InlineKeyboardButton();
        result.setText(action.getText());
//        if (action.getId() == 4L){
//            result.
//        }
//        KeyboardButton keyboardButton = new KeyboardButton();
//        keyboardButton.setRequestContact(true);

        result.setCallbackData(action.getTargetChapterId().toString());
        return result;
    }

}
