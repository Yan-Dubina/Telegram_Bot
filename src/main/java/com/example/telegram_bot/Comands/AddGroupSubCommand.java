package com.example.telegram_bot.Comands;

import com.example.telegram_bot.Client.TelegramBotGroupClient;
import com.example.telegram_bot.Services.GroupSubService;
import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.dto.GroupRequestArgs;
import com.example.telegram_bot.repository.entity.GroupSub;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.stream.Collectors;

import static com.example.telegram_bot.Comands.CommandName.ADD_GROUP_SUB;
import static com.example.telegram_bot.Comands.CommandUtils.getChatId;
import static com.example.telegram_bot.Comands.CommandUtils.getMessage;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class AddGroupSubCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    private final TelegramBotGroupClient telegramBotGroupClient;
    private final GroupSubService groupSubService;


    public AddGroupSubCommand(SendBotMessageService sendBotMessageService, TelegramBotGroupClient telegramGroupClient,
                              GroupSubService groupSubService) {
        this.sendBotMessageService = sendBotMessageService;
        this.telegramBotGroupClient = telegramGroupClient;
        this.groupSubService = groupSubService;
    }

    @Override
    public void execute(Update update) {
        if (getMessage(update).equalsIgnoreCase(ADD_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update).toString());
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = getChatId(update).toString();
        if (isNumeric(groupId)) {
            GroupDiscussionInfo groupById = telegramBotGroupClient.getGroupById(Integer.parseInt(groupId));
            if (isNull(groupById.getId())) {
                sendGroupNotFound(chatId, groupId);
            }
            GroupSub savedGroupSub = groupSubService.save(chatId, groupById);
            sendBotMessageService.sendMessage(chatId, "Подписал на группу " + savedGroupSub.getTitle());
        } else {
            sendGroupNotFound(chatId, groupId);
        }
    }
    private void sendGroupNotFound(String chatId, String groupId) {
        String groupNotFoundMessage = "Нет группы с ID = \"%s\"";
        sendBotMessageService.sendMessage(chatId, String.format(groupNotFoundMessage, groupId));
    }

    private void sendGroupIdList(String chatId) {
        String groupIds = telegramBotGroupClient.getGroupList(GroupRequestArgs.builder().build()).stream()
                .map(group -> String.format("%s - %s \n", group.getTitle(), group.getId()))
                .collect(Collectors.joining());

        String message = "Чтобы подписаться на группу - передай комадну вместе с ID группы. \n" +
                "Например: /addGroupSub 16. \n\n" +
                "я подготовил список всех групп - выберай какую хочешь :) \n\n" +
                "имя группы - ID группы \n\n" +
                "%s";

        sendBotMessageService.sendMessage(chatId, String.format(message, groupIds));
    }
}
