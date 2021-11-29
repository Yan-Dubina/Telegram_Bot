package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Services.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.telegram_bot.Comands.CommandName.*;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    static final String HELP_MESSAGE= String.format("✨<b>Дотупные команды</b>✨\n\n"

                    + "<b>Начать\\закончить работу с ботом</b>\n"
                    + "%s - начать работу со мной\n"
                    + "%s - приостановить работу со мной\n"
                    + "%s - получить помощь в работе со мной\n"
                    + "%s - доватить нового пользователя\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName(), NEW_USER.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(),HELP_MESSAGE);
    }
}