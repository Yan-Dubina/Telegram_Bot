package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.TelegramUserService;
import com.example.telegram_bot.repository.entity.TelegramUser;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;

    public final static String START_MESSAGE = "Привет. готов вкалывать";

    public StartCommand(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramUserService = telegramUserService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        if (telegramUserService.findByChatId(chatId).isPresent()) {
            telegramUserService.findByChatId(chatId).ifPresent(
                    telegramUser -> {
                        telegramUser.setActive(true);
                        telegramUserService.save(telegramUser);
                    });

        }
        else{
            TelegramUser telegramUser = new TelegramUser();
            telegramUser.setActive(true);
            telegramUser.setChatId(chatId);
            telegramUserService.save(telegramUser);
        }

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);

    }
}
