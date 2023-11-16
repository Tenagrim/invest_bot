package com.tenagrim.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
@EnableCaching
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
