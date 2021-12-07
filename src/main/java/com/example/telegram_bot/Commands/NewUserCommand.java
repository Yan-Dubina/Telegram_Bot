package com.example.telegram_bot.Commands;

import com.example.telegram_bot.Services.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NewUserCommand implements Command{
    private final SendBotMessageService sendBotMessageService;

    public final static String NEW_USER_MESSAGE = "Привет, новый пользователь.Готов вкалывать";

    public NewUserCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NEW_USER_MESSAGE);

    }
}
