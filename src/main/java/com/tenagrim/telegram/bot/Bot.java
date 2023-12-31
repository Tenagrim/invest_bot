package com.tenagrim.telegram.bot;

import com.tenagrim.telegram.exception.NotFoundException;
import com.tenagrim.telegram.model.config.BotConfig;
import com.tenagrim.telegram.model.config.BotConfigPropertyValue;
import com.tenagrim.telegram.repository.BotConfigRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
//    private final BotConfigService botConfigService;
    private final BotConfigRepository botConfigRepository;

    private BotConfig botConfig;

    @PostConstruct
    public void postConstruct(){
//        this.botConfig = botConfigService.getConfigBySysName(botSysName);
        this.botConfig = botConfigRepository.findBySysName(botSysName).orElseThrow(NotFoundException::new);
    }

    @Override
    public void onUpdateReceived(Update update) {
        var messagesToSend = updateReceiver.handle(update, botConfig);
        if (messagesToSend != null && !messagesToSend.isEmpty()) {
            messagesToSend.forEach(this::executeWithExceptionCheck);
        }
    }

    public void executeWithExceptionCheck(PartialBotApiMethod<? extends Serializable> sendMessage) {
        try {
            if (sendMessage instanceof BotApiMethod){
            sendApiMethod((BotApiMethod<? extends Serializable>) sendMessage);
            } else if (sendMessage instanceof SendDocument) { /// TODO: needs refactor
                execute((SendDocument) sendMessage);
            }
        } catch (TelegramApiException e) {
            log.error("oops", e);
        }
    }

    public void setBotConfig(BotConfig botConfig) {
        this.botConfig = botConfig;
    }
}