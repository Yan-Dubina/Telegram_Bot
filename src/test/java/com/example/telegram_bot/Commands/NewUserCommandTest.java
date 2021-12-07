package com.example.telegram_bot.Commands;

import static com.example.telegram_bot.Commands.CommandName.NEW_USER;
public class NewUserCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
       return NEW_USER.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return NewUserCommand.NEW_USER_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new NewUserCommand(sendBotMessageService);
    }
}
