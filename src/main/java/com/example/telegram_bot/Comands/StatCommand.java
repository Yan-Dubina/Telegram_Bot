package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.TelegramUserService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatCommand  implements Command{

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String STAT_MESSAGE = "Привет. Telegram-bot использует аж %s человек! ";

    public StatCommand(SendBotMessageService sendBotMessageService,TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService=telegramUserService;
    }

    @Override
    public void execute(Update update) {
        int activiteUserCount= telegramUserService.retrieveAllActiveUsers().size();
        sendBotMessageService.sendMessage(update.getMessage()
                .getChatId()
                .toString(),String.format( STAT_MESSAGE,activiteUserCount));

    }
}
