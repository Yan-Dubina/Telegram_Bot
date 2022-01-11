package com.example.telegram_bot.Client;

import com.example.telegram_bot.dto.PostInfo;
import kong.unirest.GenericType;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBotPostClientImpl implements TelegramBotPostClient{

    private final String javarushApiPostPath;

    public TelegramBotPostClientImpl(@Value("${javarush.api.path}") String javarushApi) {
        this.javarushApiPostPath=javarushApi+"/posts";
    }

    @Override
    public List<PostInfo> findNewPosts(Integer groupId, Integer lastPostId) {
        List<PostInfo> lastPostByGroup = Unirest.get(javarushApiPostPath)
                .queryString("order","NEW")
                .queryString("groupKid",groupId)
                .queryString("limit",15)
                .asObject(new GenericType<List<PostInfo>>() {
                }).getBody();
        List<PostInfo> newPosts = new ArrayList<>();
        for (PostInfo post: lastPostByGroup) {
            if (lastPostId.equals(post.getId())){
                return newPosts;
            }
            newPosts.add(post);

        }
        return newPosts;
    }
}
