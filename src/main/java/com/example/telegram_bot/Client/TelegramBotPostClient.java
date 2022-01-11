package com.example.telegram_bot.Client;

import com.example.telegram_bot.dto.PostInfo;

import java.util.List;

public interface TelegramBotPostClient {


    List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId);
}
