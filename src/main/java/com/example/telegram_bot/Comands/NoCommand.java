package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Services.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static String NO_COMMAND = "Чтобы посмотреть список  команд напиши: /help  !";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),NO_COMMAND);

    }
}
