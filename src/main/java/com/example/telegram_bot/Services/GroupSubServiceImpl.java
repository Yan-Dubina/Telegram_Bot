package com.example.telegram_bot.Services;

import com.example.telegram_bot.dto.GroupDiscussionInfo;
import com.example.telegram_bot.repository.GroupSubRepository;
import com.example.telegram_bot.repository.entity.GroupSub;
import com.example.telegram_bot.repository.entity.TelegramUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.Optional;

@Service
public class GroupSubServiceImpl implements  GroupSubService{

    private final GroupSubRepository groupSubRepository;
    private final TelegramUserService telegramUserService;

    @Autowired
    public GroupSubServiceImpl(GroupSubRepository groupSubRepository, TelegramUserService telegramUserService){
        this.groupSubRepository=groupSubRepository;
        this.telegramUserService=telegramUserService;
    }

    @Override
    public GroupSub save(String chatId, GroupDiscussionInfo groupDiscussionInfo) {

        TelegramUser telegramUser= telegramUserService.findByChatId(chatId).orElseThrow(NotFoundException::new);
        GroupSub groupSub;
        Optional<GroupSub> groupSubFromDB = groupSubRepository.findById(groupDiscussionInfo.getId());
        if(groupSubFromDB.isPresent()) {
            groupSub = groupSubFromDB.get();
            Optional<TelegramUser> first = groupSub.getUsers().stream()
                    .filter(it -> it.getChatId().equalsIgnoreCase(chatId))
                    .findFirst();
            if (!first.isPresent()) {
                groupSub.addUser(telegramUser);
            }
        }else{
            groupSub=new GroupSub();
            groupSub.addUser(telegramUser);
            groupSub.setId(groupDiscussionInfo.getId());
            groupSub.setTitle(groupDiscussionInfo.getTitle());
        }
        return groupSubRepository.save(groupSub);
    }
}
