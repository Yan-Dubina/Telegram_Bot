package com.example.telegram_bot.Services;

import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.repository.entity.GroupSub;

import java.util.List;
import java.util.Optional;

public interface GroupSubService {
    GroupSub save(String ChatId, GroupDiscussionInfo groupDiscussionInfo);
    Optional<GroupSub> findById(Integer id);
    List<GroupSub> findAll();
    GroupSub save(GroupSub groupSub);
}
