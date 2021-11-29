package com.example.telegram_bot.Services;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface SendBotMessageService {
    void sendMessage(String chatId,String message);
}
