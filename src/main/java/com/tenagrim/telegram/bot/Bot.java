package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.model.BotConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Component
@Getter
@RequiredArgsConstructor
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;
    @Value("${bot.sysname}")
    private String botSysName;
    private final UpdateReceiver updateReceiver;

    private BotConfig botConfig;


    @Override
    public void onUpdateReceived(Update update) {
        List<PartialBotApiMethod<? extends Serializable>> messagesToSend = updateReceiver.handle(update, botConfig);
        if (messagesToSend != null && !messagesToSend.isEmpty()) {
            messagesToSend.forEach(this::executeWithExceptionCheck);
        }
    }

    public void executeWithExceptionCheck(PartialBotApiMethod<? extends Serializable> sendMessage) {
        try {
            sendApiMethod((BotApiMethod<? extends Serializable>) sendMessage);
        } catch (TelegramApiException e) {
            log.error("oops");
            e.printStackTrace();
        }
    }

    public void setBotConfig(BotConfig botConfig) {
        this.botConfig = botConfig;
    }
}