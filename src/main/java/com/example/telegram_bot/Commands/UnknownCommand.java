package com.example.telegram_bot.Commands;

import com.example.telegram_bot.Services.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class UnknownCommand implements Command {

    public static final String UNKNOWN_MESSAGE = "Я тебя не  понимаю! Введи  нормальную  команду!" +
            "\\uD83D\\uDE1F, напишите /help чтобы узнать что я понимаю.";

    private final SendBotMessageService sendBotMessageService;

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService=sendBotMessageService;
    }


    @Override
    public void execute(Update update) {

        sendBotMessageService.sendMessage(update.getMessage().getChatId()
                .toString(),UNKNOWN_MESSAGE);

    }
}
