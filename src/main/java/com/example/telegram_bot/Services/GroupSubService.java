package com.example.telegram_bot.Services;

import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.repository.entity.GroupSub;

public interface GroupSubService {
    GroupSub save(String ChatId, GroupDiscussionInfo groupDiscussionInfo);
}
