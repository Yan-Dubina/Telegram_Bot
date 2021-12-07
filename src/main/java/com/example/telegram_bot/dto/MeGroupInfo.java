package com.example.telegram_bot.dto;

import lombok.Data;

@Data
public class MeGroupInfo {
    private MeGroupStatus status;
    private Integer userGroupId;
}
