package com.example.telegram_bot.Services;


import com.example.telegram_bot.Client.TelegramBotGroupClient;
import com.example.telegram_bot.Client.TelegramBotPostClient;
import com.example.telegram_bot.dto.PostInfo;
import com.example.telegram_bot.repository.entity.GroupSub;
import com.example.telegram_bot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FindNewArticleServiceImpl implements FindNewArticleService{

    public static final String JAVARUSH_WEB_POST_FORMAT ="https://javarush.ru/groups/posts/%s";

    private final GroupSubService groupSubService;
    private final TelegramBotPostClient telegramBotPostClient;
    private final SendBotMessageService sendBotMessageService;

    @Autowired
    public FindNewArticleServiceImpl(GroupSubService groupSubService,
                                 TelegramBotPostClient telegramBotPostClient,
                                 SendBotMessageService sendBotMessageService){
        this.groupSubService=groupSubService;
        this.sendBotMessageService=sendBotMessageService;
        this.telegramBotPostClient=telegramBotPostClient;
    }



    @Override
    public void findNewArticles() {
       try {
           groupSubService.findAll().forEach(groupSub -> {

               List<PostInfo> newPost = telegramBotPostClient.findNewPosts(groupSub.getId(),groupSub.getLastArticleId());

               setNewLastArticleId(groupSub,newPost);

               notifySubscribersAboutNewArticles(groupSub,newPost);
           });
       }
        catch(Error error)
    {
        System.out.println(error.getMessage());
    }

    }
    private void notifySubscribersAboutNewArticles(GroupSub groupSub,List<PostInfo> newPost){
        Collections.reverse(newPost);
        List<String> messagesWithNewArticles = newPost.stream()
                .map(post -> String.format("✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                        "<b>Описание:</b> %s\n\n" +
                        "<b>Ссылка:</b> %s\n","✨Вышла новая статья <b>%s</b> в группе <b>%s</b>.✨\n\n" +
                        "<b>Описание:</b> %s\n\n" +
                        "<b>Ссылка:</b> %s\n",
                        post.getTitle(),groupSub.getTitle(),post.getDescription(),getPostUrl(post.getKey())))
                .collect(Collectors.toList());

        groupSub.getUsers().stream()
                .filter(TelegramUser::isActive)
                .forEach(it -> sendBotMessageService.sendMessage(it.getChatId(), String.valueOf(messagesWithNewArticles)));
    }

    private void setNewLastArticleId(GroupSub groupSub, List<PostInfo> newPosts){

        newPosts.stream().mapToInt(PostInfo::getId).max()
                .ifPresent(id->{

                    groupSub.setLastArticleId(id);
                    groupSubService.save(groupSub);
                });
    }
    private String getPostUrl(String key){
        return String.format(JAVARUSH_WEB_POST_FORMAT,key);
    }
}
