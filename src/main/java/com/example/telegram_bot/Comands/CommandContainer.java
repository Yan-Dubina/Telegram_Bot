package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Client.TelegramBotGroupClient;
import com.example.telegram_bot.Services.GroupSubService;
import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.TelegramUserService;
import com.google.common.collect.ImmutableMap;

import static com.example.telegram_bot.Comands.CommandName.*;

public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService, TelegramUserService telegramUserService, TelegramBotGroupClient telegramBotGroupClient,
                            GroupSubService groupSubService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService, telegramUserService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService, telegramUserService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO_COMMAND.getCommandName(), new NoCommand(sendBotMessageService))
                .put(NEW_USER.getCommandName(), new NewUserCommand(sendBotMessageService))
                .put(STAT.getCommandName(), new StatCommand(sendBotMessageService, telegramUserService))
                .put(ADD_GROUP_SUB.getCommandName(), new AddGroupSubCommand(sendBotMessageService, telegramBotGroupClient, groupSubService))
                .put(LIST_GROUP_SUB.getCommandName(), new ListGroupSubCommand(sendBotMessageService, telegramUserService))
                .build();
        unknownCommand = new UnknownCommand(sendBotMessageService);
    }

    public Command retriveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
