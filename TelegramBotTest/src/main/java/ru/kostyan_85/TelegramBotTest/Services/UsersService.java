package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.kostyan_85.TelegramBotTest.Bot;
import ru.kostyan_85.TelegramBotTest.Entity.Users;
import ru.kostyan_85.TelegramBotTest.Repository.UsersRepository;

import java.util.Optional;

@Service
public class UsersService {


//    private RestTemplate restTemplate = new RestTemplate();
//
//    String fooResourceUrl
//            = "https://backorder.ru/json/?order=desc&expired=1&by=hotness&page=1&items=50";
//    ResponseEntity<String> response= restTemplate.getForEntity(fooResourceUrl + "/1", String.class);

    @Autowired
  private   MessagesService messagesService;
    @Autowired
    private UsersRepository repository;

    @Autowired
    private Bot bot;

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

    /**
     * получение исходящего сообщения
     *
     * @return сообщение
     */
    public String getOutputMessage() {
        return bot.getOutputMessage();
    }

    public long getUserId(Update update) {
        User sender = update.getMessage().getFrom();
        return sender.getId();

    }

    public Users userToEntity(User user, Update update) {
        Users users = new Users();
        users.setUserTelegramId(Long.valueOf(user.getId()));
//        user.getFirstName()!=null ? user.getFirstName() : ""
        users.setUserName(user.getFirstName()!=null ? user.getFirstName() : "");
        users.setLastMessageAt(getInputMessage(update));
        return users;
    }

    public boolean isCheckExistsUser(User user, Update update){
        if (repository.hasUserById(getUserId(update))){
            saveUserToBase(user, update);
            return true;
        }
        else updateUserToBase(update);
        return false;
    }

    public void saveUserToBase(User user, Update update) {

//        if (!repository.hasUserById(getUserId(update))) {
            repository.save(userToEntity(user, update));
//            return true;
//        }
//        return false;

    }
//@Transactional
    public void updateUserToBase( Update update) {

    Optional<Users> userById = repository.findByUserTelegramId(getUserId(update));

   if (userById.isPresent()){
       Users users = userById.get();

       users.setLastMessageAt(getInputMessage(update));
       repository.save(users);
   }


}
    public boolean saveOrUpdate(){

        return false;
    }

}
