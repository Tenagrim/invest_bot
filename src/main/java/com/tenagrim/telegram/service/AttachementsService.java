package com.tenagrim.telegram.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AttachementsService {

    @Value("${bot.attachements-path}")
    private String attachementsPath;

}
