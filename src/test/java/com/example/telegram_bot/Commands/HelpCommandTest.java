package com.example.telegram_bot.Commands;
import static com.example.telegram_bot.Commands.CommandName.HELP;
public class HelpCommandTest extends AbstractCommandTest{

    @Override
    String getCommandName() {
        return HELP.getCommandName();
    }

    @Override
    String getCommandMessage() {
        return HelpCommand.HELP_MESSAGE;
    }

    @Override
    Command getCommand() {
        return new HelpCommand(sendBotMessageService);
    }
}
