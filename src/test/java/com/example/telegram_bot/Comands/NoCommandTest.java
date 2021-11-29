package com.example.telegram_bot.Comands;

import static com.example.telegram_bot.Comands.CommandName.NO_COMMAND;
import static com.example.telegram_bot.Comands.NoCommand.NO_COMMAND_MESSAGE;
public class NoCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return NO_COMMAND.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NO_COMMAND_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NoCommand(sendBotMessageService);
    }
}
