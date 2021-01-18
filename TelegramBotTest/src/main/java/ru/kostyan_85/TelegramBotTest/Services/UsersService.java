package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kostyan_85.TelegramBotTest.Entity.Users;
import ru.kostyan_85.TelegramBotTest.Repository.UsersRepository;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsersService {


    @Autowired
    private MessagesService messagesService;

    @Autowired
    private UsersRepository repository;


    /**
     * получение имени пользователя
     *
     * @param update
     * @return имя пользователя
     */
    public String getUserName(Update update) {
        User sender = update.getMessage().getFrom();
        return sender.getFirstName();
    }

    public ArrayList<Users> getAllUserTelegramId() {
        return repository.findAllByUserTelegramId();
    }

    /**
     * получение Id пльзователя
     *
     * @return id пользователя
     */
    public long getUserId(Update update) {
        User sender = update.getMessage().getFrom();
        return sender.getId();
    }

    /**
     *
     */
    //TODO как правильно написать коммент?
    public Users userToEntity(User user, Update update) {
        Users users = new Users();

        users.setUserTelegramId(Long.valueOf(user.getId()));
        users.setUserName(user.getFirstName() != null ? user.getFirstName() : "");
        users.setLastMessageAt(messagesService.getInputMessage(update));
        return users;
    }

    /**
     * проверка наличия пользователя в БД
     */

    public boolean isCheckExistsUser(User user, Update update) {
        if (!repository.hasUserById(getUserId(update))) {
            saveUserToBase(user, update);
            return true;
        } else updateUserToBase(update);
        return false;
    }

    /**
     * сохранение пользоателя в БД
     */
    public void saveUserToBase(User user, Update update) {
        repository.save(userToEntity(user, update));
    }

    /**
     * обновление пользователя ели он уже существует в БД
     */
    public void updateUserToBase(Update update) {
        Optional<Users> userById = repository.findByUserTelegramId(getUserId(update));
        if (userById.isPresent()) {
            Users users = userById.get();
            users.setLastMessageAt(messagesService.getInputMessage(update));
            repository.save(users);
        }
    }

    public static void main(String[] args) {
        UsersService service = new UsersService();
        service.getAllUserTelegramId();
    }
}
