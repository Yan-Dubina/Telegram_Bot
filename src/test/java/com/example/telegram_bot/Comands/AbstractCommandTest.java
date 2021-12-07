package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Components.TelegramBot;
import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.SendBotMessageServiceImpl;
import com.example.telegram_bot.Services.TelegramUserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public abstract class AbstractCommandTest {
    protected TelegramBot telegramBot = Mockito.mock(TelegramBot.class);
    protected SendBotMessageService sendBotMessageService = new SendBotMessageServiceImpl(telegramBot);
    protected TelegramUserService telegramUserService=Mockito.mock(TelegramUserService.class);

    abstract String getCommandName();

    abstract String getCommandMessage();

    abstract  Command getCommand();

    @Test
    public  void shouldPropertlyExecuteCommand()throws TelegramApiException{

        Long chatId =12346789L;

        Update update = new Update();
        Message message = Mockito.mock(Message.class);
        Mockito.when(message.getChatId()).thenReturn(chatId);
        Mockito.when(message.getText()).thenReturn(getCommandName());
        update.setMessage(message);

        SendMessage sendMessage =new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText(getCommandMessage());
        sendMessage.enableHtml(true);


        getCommand().execute(update);

        Mockito.verify(telegramBot).execute(sendMessage);

    }

}
