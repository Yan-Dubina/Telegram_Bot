package com.example.telegram_bot.Commands;
import static  com.example.telegram_bot.Commands.CommandName.START;
import  static  com.example.telegram_bot.Commands.StartCommand.START_MESSAGE;

public class StartCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return START.getCommandName();
    }
    @Override
    String getCommandMessage(){
        return START_MESSAGE;
    }
    @Override
    Command getCommand(){
        return  new StartCommand(sendBotMessageService,telegramUserService);
    }

}
