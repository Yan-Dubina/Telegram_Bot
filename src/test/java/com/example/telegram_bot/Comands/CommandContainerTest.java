package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.TelegramUserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;



@DisplayName("Unit-level testing CommandContainer")
class CommandContainerTest {
    private CommandContainer commandContainer;
    private TelegramUserService telegramUserService;

    @BeforeEach
    public void init() {
        SendBotMessageService sendBotMessageService = Mockito.mock(SendBotMessageService.class);
        telegramUserService= Mockito.mock(TelegramUserService.class);
        commandContainer = new CommandContainer(sendBotMessageService,telegramUserService);

    }

    @Test
    public void shouldGetAllTheExitingCommands() {
        Arrays.stream(CommandName.values())
                .forEach(CommandName -> {
                    Command command = commandContainer.retriveCommand(CommandName.getCommandName());
                    Assertions.assertNotEquals(UnknownCommand.class, command.getClass());
                });
    }

    @Test
    public void shouldReturnUnknownCommand() {

        String unknownCommand ="/asdasdasd";

        Command command = commandContainer.retriveCommand(unknownCommand);

        Assertions.assertEquals (UnknownCommand.class,command.getClass());
    }
}