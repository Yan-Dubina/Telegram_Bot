package com.example.telegram_bot.Commands;

import static com.example.telegram_bot.Commands.CommandName.NO_COMMAND;
import static com.example.telegram_bot.Commands.NoCommand.NO_COMMAND_MESSAGE;
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
