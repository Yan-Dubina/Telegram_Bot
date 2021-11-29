package com.example.telegram_bot.Comands;

import static com.example.telegram_bot.Comands.CommandName.STOP;
import static com.example.telegram_bot.Comands.StopCommand.STOP_MESSAGE;


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
        return new StopCommand(sendBotMessageService);
    }
}
