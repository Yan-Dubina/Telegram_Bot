package com.example.telegram_bot.Components;

import com.example.telegram_bot.Client.TelegramBotGroupClient;
import com.example.telegram_bot.Comands.CommandContainer;
import com.example.telegram_bot.Services.GroupSubService;
import com.example.telegram_bot.Services.SendBotMessageServiceImpl;
import com.example.telegram_bot.Services.TelegramUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.Locale;

import static com.example.telegram_bot.Comands.CommandName.*;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;
    @Value("${bot.token}")
    private String token;


    private final CommandContainer commandContainer;

    public static String COMMAND_PREFIX = "/";

    @Autowired
    public TelegramBot(TelegramUserService telegramUserService, TelegramBotGroupClient telegramBotGroupClient,
                       GroupSubService  groupSubService) {
        this.commandContainer = new CommandContainer (new SendBotMessageServiceImpl(this),telegramUserService,
                telegramBotGroupClient,groupSubService );
    }

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase(Locale.ROOT);
                commandContainer.retriveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retriveCommand(NO_COMMAND.getCommandName()).execute(update);
            }
        }


    }

}
