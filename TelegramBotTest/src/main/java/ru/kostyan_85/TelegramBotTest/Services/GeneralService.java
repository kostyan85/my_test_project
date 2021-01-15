package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kostyan_85.TelegramBotTest.Bot;
import ru.kostyan_85.TelegramBotTest.Entity.Messages;

@Service
public class GeneralService {

    @Autowired
    UsersService usersService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    Bot bot;
    public void saveUsersAndMessages( Update update){
        usersService.isCheckExistsUser(update.getMessage().getFrom(),update);
//        usersService.saveUserToBase(update.getMessage().getFrom(),update);
        messagesService.saveMessagesToBase(update);

    }
}
