package com.tenagrim.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//import org.telegram.telegrambots.ApiContextInitializer;

@SpringBootApplication
public class App {
    public static void main(String[] args) throws IOException {
//        ApiContextInitializer.init();
        SpringApplication.run(App.class, args);

        File file = new File("testfile.txt");
        FileWriter fw = new FileWriter(file);
        fw.write("--------");
        fw.close();
    }
}
