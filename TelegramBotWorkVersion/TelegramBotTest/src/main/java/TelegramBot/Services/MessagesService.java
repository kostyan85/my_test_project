package TelegramBot.Services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import TelegramBot.Bot;
import TelegramBot.Entity.Messages;
import TelegramBot.Entity.Users;
import TelegramBot.Repository.MessagesRepository;
import TelegramBot.Repository.UsersRepository;

import java.util.Optional;


@Service
public class MessagesService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private MessagesRepository messagesRepository;
    @Autowired
    private UsersService usersService;

    @Autowired
    private Bot bot;


    private static final Logger LOGGER = LoggerFactory.getLogger(MessagesService.class);

    /**
     * получение исходящего сообщения
     *
     * @return сообщение
     */
    public String getOutputMessage() {
        String msg = "";
        if (bot.getOutputMessage() != null) {
            msg = bot.getOutputMessage();
        } else {
            LOGGER.error("error getOutputMessage: сообщение не получено");
        }
        return msg;
    }
    /**
     * получение входящего сообщения
     *
     * @param update
     * @return текст сообщения
     */
    public String getInputMessage(Update update) {
        String msg = "";
        if (update.getMessage()!=null){
            Message message = update.getMessage();
            msg = message.getText();
        } else {
            LOGGER.error("error getInputMessage: сообщение не получено");
        }
        return msg;
    }


    /**
     * заполнение сущности Messages
     */
    public Messages messagesToEntity(Update update) {
        Messages messagesEntity = new Messages();

        Optional<Users> byUserId = usersRepository.findByUserTelegramId(usersService.getUserId(update));
        if (byUserId.isPresent()) {
            if (getInputMessage(update) != null) {
                messagesEntity.setInMessage(getInputMessage(update));
                messagesEntity.setOutMessage(getOutputMessage());
                messagesEntity.setUsers(byUserId.get());
            } else {
                LOGGER.error("error messagesToEntity: getInputMessage() is Null");
            }
        } else {
            LOGGER.error("error messagesToEntity: в бд не найден пользователь с таким Id");
        }

        return messagesEntity;
    }

    /**
     * сохраняем сообщения в БД
     */
    public void saveMessagesToBase(Update update) {
        try {
            messagesRepository.save(messagesToEntity(update));
        } catch (Exception e) {
            LOGGER.error("error saveMessagesToBase: {0} ", e);
        }
    }
}
