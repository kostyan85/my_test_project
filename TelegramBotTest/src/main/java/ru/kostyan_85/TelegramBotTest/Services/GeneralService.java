package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.kostyan_85.TelegramBotTest.Bot;


@Service
public class GeneralService {

    @Autowired
    UsersService usersService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    Bot bot;

    /**
     * сохраняем или обновляем пользователя и сохраняем сообщения в БД
     */
    public void saveUsersAndMessages(Update update) {
        usersService.isCheckExistsUser(update.getMessage().getFrom(), update);
        messagesService.saveMessagesToBase(update);

    }
}
