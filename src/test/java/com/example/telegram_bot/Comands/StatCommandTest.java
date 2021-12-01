package com.example.telegram_bot.Comands;

import static com.example.telegram_bot.Comands.CommandName.STAT;
import static com.example.telegram_bot.Comands.StatCommand.STAT_MESSAGE;

public class StatCommandTest extends AbstractCommandTest {
    @Override
    String getCommandName() {
        return STAT.getCommandName();
    }
    @Override
    String getCommandMessage(){
        return String.format(STAT_MESSAGE,0);
    }
    @Override
    Command getCommand(){
        return  new StartCommand(sendBotMessageService,telegramUserService);
    }

}
