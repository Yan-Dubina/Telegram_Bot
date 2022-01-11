package com.example.telegram_bot.job;


import com.example.telegram_bot.Services.FindNewArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Slf4j
@Component
public class FindNewArticlesJob {

    private final  FindNewArticleService findNewArticleService;

    @Autowired
    public FindNewArticlesJob(FindNewArticleService findNewArticlesService){
        this.findNewArticleService=findNewArticlesService;
    }
    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void findNewArticleService(){

        LocalDateTime start = LocalDateTime.now();

        log.info("Find new article job started!");

        findNewArticleService.findNewArticles();

        LocalDateTime end = LocalDateTime.now();

        log.info("Find new articles job finished. Took seconds:{}",end.toEpochSecond(ZoneOffset.UTC)-start.toEpochSecond(ZoneOffset.UTC));
    }
}
