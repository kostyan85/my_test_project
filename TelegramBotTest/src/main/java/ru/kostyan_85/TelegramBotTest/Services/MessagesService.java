package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
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
    Bot bot;

    /**
     * получение исходящего сообщения
     *
     * @return сообщение
     */
    public String getOutputMessage() {
        return bot.getOutputMessage();
    }

    /**
     * получение входящего сообщения
     *
     * @param update
     * @return текст сообщения
     */
    public String getInputMessage(Update update) {
        Message message = update.getMessage();
        return message.getText();
    }


    public Messages messagesToEntity(Update update) {
        Messages messagesEntity = new Messages();
        Optional<Users> byUserId = usersRepository.findByUserTelegramId(usersService.getUserId(update));
        messagesEntity.setInMessage(getInputMessage(update));
        messagesEntity.setOutMessage(getOutputMessage());
        messagesEntity.setUsers(byUserId.get());
        return messagesEntity;
    }

    public void saveMessagesToBase(Update update) {
        messagesRepository.save(messagesToEntity(update));
        System.out.println();
    }
}
