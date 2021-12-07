package com.example.telegram_bot.Client;

import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.dto.GroupInfo;
import com.example.telegram_bot.dto.GroupRequestArgs;
import com.example.telegram_bot.dto.GroupsCountRequestArgs;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.example.telegram_bot.dto.GroupInfoType.TECH;


class TelegramBotGroupClientTest {

    private final TelegramBotGroupClient groupClient = new TelegramBotGroupClientImpl("https://javarush.ru/api/1.0/rest");

    @Test
    public void shouldProperlyGetGroupsWithEmptyArgs() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertFalse(groupInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetWithOffSetAndLimit() {

        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupInfo> groupInfoList = groupClient.getGroupList(args);

        //then
        Assertions.assertNotNull(groupInfoList);
        Assertions.assertEquals(3, groupInfoList.size());
    }

    @Test
    public void shouldProperlyGetGroupsDiscWithEmptyArgs() {

        //given
        GroupRequestArgs args = GroupRequestArgs.builder().build();

        //when
        List<GroupDiscussionInfo> groupDiscussionInfoList = groupClient.getGroupDiscussionList(args);


        //then
        Assertions.assertNotNull(groupDiscussionInfoList);
        Assertions.assertFalse(groupDiscussionInfoList.isEmpty());
    }

    @Test
    public void shouldProperlyGetGroupDiscWithOffSetAndLimit() {
        //given
        GroupRequestArgs args = GroupRequestArgs.builder()
                .offset(1)
                .limit(3)
                .build();

        //when
        List<GroupDiscussionInfo> groupList = groupClient.getGroupDiscussionList(args);

        //then
        Assertions.assertNotNull(groupList);
        Assertions.assertEquals(3, groupList.size());
    }

    @Test
    public void shouldProperlyGetGroupCount() {
        //given
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder().build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(31, groupCount);
    }
    @Test
    public void shouldProperlyGetGroupTECHCount() {
        //given
        GroupsCountRequestArgs args = GroupsCountRequestArgs.builder()
                .type(TECH)
                .build();

        //when
        Integer groupCount = groupClient.getGroupCount(args);

        //then
        Assertions.assertEquals(7, groupCount);
    }

    @Test
    public void shouldProperlyGetGroupById() {
        //given
        Integer androidGroupId = 16;

        //when
        GroupDiscussionInfo groupById = groupClient.getGroupById(androidGroupId);

        //then
        Assertions.assertNotNull(groupById);
        Assertions.assertEquals(16, groupById.getId());
        Assertions.assertEquals(TECH.toString(),groupById.getType());
        Assertions.assertEquals("android", groupById.getKey());
    }
}