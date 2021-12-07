package com.example.telegram_bot.Commands;


import static com.example.telegram_bot.Commands.UnknownCommand.UNKNOWN_MESSAGE;

public class UnknownCommandTest extends AbstractCommandTest {

    @Override
    String getCommandName() {
        return "/asdasdasd";
    }

    @Override
    String getCommandMessage() {
        return UNKNOWN_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new UnknownCommand(sendBotMessageService);
    }
}
