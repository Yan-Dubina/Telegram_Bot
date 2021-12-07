package com.example.telegram_bot;

import jdk.nashorn.internal.runtime.logging.DebugLogger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.logging.LoggingApplicationListener;

@SpringBootApplication
public class TelegramBotApplication {


    public static void main(String[] args) {
        System.out.println(LoggingApplicationListener.CONFIG_PROPERTY);
        SpringApplication.run(TelegramBotApplication.class, args);
    }

}
