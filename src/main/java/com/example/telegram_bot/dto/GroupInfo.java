package com.example.telegram_bot.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GroupInfo {
    private String avatarUrl;
    private String createTime;
    private String description;
    private Integer id;
    private String key;
    private Integer levelToEditior;
    private MeGroupInfo megroupInfo;
    private String pictureUrl;
    private String title;
    private String type;
    private Integer usersCount;
    private GroupVisibilityStatus visibilytyStatus;

}
