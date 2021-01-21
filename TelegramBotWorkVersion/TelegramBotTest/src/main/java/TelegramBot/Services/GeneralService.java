package TelegramBot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import TelegramBot.Bot;


@Service
public class GeneralService {

    @Autowired
   private UsersService usersService;

    @Autowired
    private MessagesService messagesService;

    @Autowired
    private Bot bot;

    /**
     * сохраняем или обновляем пользователя и сохраняем сообщения в БД
     */
    public void saveUsersAndMessages(Update update) {
        usersService.isCheckExistsUser(update.getMessage().getFrom(), update);
        messagesService.saveMessagesToBase(update);

    }
}
