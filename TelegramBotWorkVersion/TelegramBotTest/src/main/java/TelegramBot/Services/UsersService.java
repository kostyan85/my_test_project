package TelegramBot.Services;

import TelegramBot.Entity.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import TelegramBot.Repository.UsersRepository;

import java.util.ArrayList;;
import java.util.Optional;

@Service
public class UsersService {


    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UsersRepository userRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersService.class);

    public ArrayList<Users> getAllUserTelegramId() {
        ArrayList<Users> arrUsers = new  ArrayList();
        if (!userRepository.findAllByUserTelegramId().isEmpty()) {
            arrUsers = userRepository.findAllByUserTelegramId();
        } else {
            LOGGER.error("error getUserId");
        }
        return arrUsers;
    }

    /**
     * получение Id пльзователя
     *
     * @return id пользователя
     */
    public long getUserId(Update update) {
        long longId = -1L;
        if (update.getMessage().getFrom() != null) {
            User sender = update.getMessage().getFrom();
            longId = sender.getId();
        } else {
            LOGGER.error("error getUserId");
        }
        return longId;

    }

    /**
     * заполнение сущности Users
     */

    public Users userToEntity(User user, Update update) {
        Users users = null;
        try {
            users = new Users();
            users.setUserTelegramId(Long.valueOf(user.getId()));
            users.setUserName(user.getFirstName() != null ? user.getFirstName() : "");
            users.setLastMessageAt(messagesService.getInputMessage(update));
        } catch (Exception e) {
            LOGGER.error("error userToEntity");
        }
        return users;
    }

    /**
     * проверка наличия пользователя в БД
     */

    public void isCheckExistsUser(User user, Update update) {
        try {
            if (!userRepository.hasUserById(getUserId(update))) {
                saveUserToBase(user, update);
            } else updateUserToBase(update);
        } catch (Exception e) {
            LOGGER.error("error isCheckExistsUser");
        }
    }

    /**
     * сохранение пользоателя в БД
     */

    public void saveUserToBase(User user, Update update) {
        try {
            userRepository.save(userToEntity(user, update));
        } catch (Exception e) {
            LOGGER.error("error saveUserToBase: {0} ", e);

        }
    }

    /**
     * обновление пользователя ели он уже существует в БД
     */
    public void updateUserToBase(Update update) {
        Optional<Users> userById = userRepository.findByUserTelegramId(getUserId(update));
        if (userById.isPresent()) {
            Users users = userById.get();
            users.setLastMessageAt(messagesService.getInputMessage(update));
            userRepository.save(users);
        } else {
            LOGGER.error("error updateUserToBase: пользователь не обновлен");
        }
    }

}
