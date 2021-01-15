package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kostyan_85.TelegramBotTest.Bot;
import ru.kostyan_85.TelegramBotTest.Entity.Messages;
import ru.kostyan_85.TelegramBotTest.Entity.Users;
import ru.kostyan_85.TelegramBotTest.Repository.MessagesRepository;
import ru.kostyan_85.TelegramBotTest.Repository.UsersRepository;

import java.util.Optional;

@Service
public class MessagesService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UsersService usersService;
    @Autowired
    private Bot bot;

    public Messages messagesToEntity(Update update) {

        Messages messagesEntity = new Messages();
        Optional<Users> byUserId = usersRepository.findByUserTelegramId(usersService.getUserId(update));
        messagesEntity.setInMessage(usersService.getInputMessage(update));
        messagesEntity.setOutMessage(usersService.getOutputMessage());
        messagesEntity.setUsers(byUserId.get());
        return messagesEntity;

////        users.setUserName(user.getFirstName() != null ? user.getFirstName() : "");
//
//        Users users = byUserId.isPresent() ? byUserId.get() : new Users();
////        messagesEntity.setUsers(byUserId.get() != null ? byUserId.get() : new Users());
    }

    public void saveMessagesToBase(Update update) {
        messagesRepository.save(messagesToEntity(update));
        System.out.println();
    }
}
