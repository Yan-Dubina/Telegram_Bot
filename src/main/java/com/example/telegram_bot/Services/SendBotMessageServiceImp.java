package com.example.telegram_bot.Services;

import com.example.telegram_bot.Components.TelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class SendBotMessageServiceImp implements SendBotMessageService {

    private final TelegramBot telegramBot;


    public SendBotMessageServiceImp(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;

    }


    @Override
    public void sendMessage(String chatId, String message) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.enableHtml(true);
        sendMessage.setText(message);

        try {
            telegramBot.execute(sendMessage);
        } catch (TelegramApiException e) {

            e.printStackTrace();
        }
    }
}
