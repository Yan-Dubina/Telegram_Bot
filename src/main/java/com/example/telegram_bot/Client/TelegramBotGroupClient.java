package com.example.telegram_bot.Client;

import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.dto.GroupInfo;
import com.example.telegram_bot.dto.GroupRequestArgs;
import com.example.telegram_bot.dto.GroupsCountRequestArgs;

import java.util.List;

public interface TelegramBotGroupClient {

    List<GroupInfo> getGroupList(GroupRequestArgs requestArgs);
    List<GroupDiscussionInfo> getGroupDiscussionList(GroupRequestArgs requestArgs);
    Integer getGroupCount(GroupsCountRequestArgs groupsCountRequestArgs);
    GroupDiscussionInfo getGroupById(Integer id);
}
