package com.example.telegram_bot.Comands;
import static  com.example.telegram_bot.Comands.CommandName.START;
import  static  com.example.telegram_bot.Comands.StartCommand.START_MESSAGE;

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
        return  new StartCommand(sendBotMessageService);
    }

}
