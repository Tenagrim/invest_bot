package com.tenagrim.telegram.service;

import com.tenagrim.telegram.bot.Bot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BotResolver {
    private final Bot bot;

    public Bot getBot() {
        return bot;
    }
}
