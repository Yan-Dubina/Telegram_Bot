package com.example.telegram_bot.Commands;

import com.example.telegram_bot.Services.GroupSubService;
import com.example.telegram_bot.Services.SendBotMessageService;
import com.example.telegram_bot.Services.TelegramUserService;
import com.example.telegram_bot.repository.entity.GroupSub;
import com.example.telegram_bot.repository.entity.TelegramUser;

import javax.ws.rs.NotFoundException;

import org.springframework.util.CollectionUtils;
import org.telegram.telegrambots.meta.api.objects.Update;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.telegram_bot.Commands.CommandName.DELETE_GROUP_SUB;
import static com.example.telegram_bot.Commands.CommandUtils.getChatId;
import static com.example.telegram_bot.Commands.CommandUtils.getMessage;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.SPACE;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class DeleteGroupSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final TelegramUserService telegramUserService;
    private final GroupSubService groupSubService;

    public DeleteGroupSubCommand(SendBotMessageService sendBotMessageService,
                                 TelegramUserService telegramUserService,
                                 GroupSubService groupSubService) {

        this.groupSubService = groupSubService;
        this.telegramUserService = telegramUserService;
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {

        if (getMessage(update).equalsIgnoreCase(DELETE_GROUP_SUB.getCommandName())) {
            sendGroupIdList(getChatId(update).toString());
            return;
        }
        String groupId = getMessage(update).split(SPACE)[1];
        String chatId = (getChatId(update)).toString();

        if (isNumeric(groupId)) {
            Optional<GroupSub> optionalGroupSub = groupSubService
                    .findById(Integer.valueOf(groupId));
            if (optionalGroupSub.isPresent()) {
                GroupSub groupSub = optionalGroupSub.get();
                TelegramUser telegramUser = telegramUserService.findByChatId(chatId)
                        .orElseThrow(NotFoundException::new);
                groupSub.getUsers().remove(telegramUser);
                groupSubService.save(groupSub);
                sendBotMessageService.sendMessage(chatId, format("Удалена подписка на группу: %s",
                        groupSub.getTitle()));

            } else {
                sendBotMessageService.sendMessage(chatId, format("Такой группы не найдено!"));
            }
        } else {
            sendBotMessageService.sendMessage(chatId, "неправильный формат ID группы.\n" +
                    "ID должно быть целым положительным числом!");
        }


    }

    private void sendGroupIdList(String chatId) {

        String message;

        List<GroupSub> groupSub = telegramUserService.findByChatId(chatId)
                .orElseThrow(NotFoundException::new)
                .getGroupSubs();
        if (CollectionUtils.isEmpty(groupSub)) {
            message = "Пока нет подписок на группы. Чтобы добавить подписку напиши /addGroupSub";
        } else {
            message ="Чтобы удалить подписку на группу - передай комадну вместе с ID группы. \n" +
                    "Например: /deletegroupsub 16 \n\n" +
                    "я подготовил список всех групп, на которые ты подписан) \n\n" +
                    "имя группы - ID группы \n\n" +
                    "%s";
        }
        String userGroupSubData=groupSub.stream()
                .map(group -> format("%s - %s \n",group.getTitle(),group.getId()))
                .collect(Collectors.joining());
        sendBotMessageService.sendMessage(chatId,format(message,userGroupSubData));

    }
}
