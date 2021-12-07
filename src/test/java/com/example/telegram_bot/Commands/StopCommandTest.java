package com.example.telegram_bot.Commands;

import static com.example.telegram_bot.Commands.CommandName.STOP;
import static com.example.telegram_bot.Commands.StopCommand.STOP_MESSAGE;


public class StopCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return STOP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return STOP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new StopCommand(sendBotMessageService,telegramUserService);
    }
}
