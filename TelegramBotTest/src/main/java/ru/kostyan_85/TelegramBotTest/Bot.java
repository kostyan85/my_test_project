package ru.kostyan_85.TelegramBotTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kostyan_85.TelegramBotTest.Services.GeneralService;
import ru.kostyan_85.TelegramBotTest.Services.UsersService;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    GeneralService generalService;


    @Autowired
    private UsersService usersService;

    private String outputMessage = "Hi";

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }

    String name;
    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {


//        update.getUpdateId();
//        SendMessage sendMessage = new SendMessage().setChatId(update.getMessage().getChatId());

//        if (update.getMessage().getText().equals("Hi")){
//
//            try {
//                execute(sendMessage.setText("Привет привет"));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//            User sender = update.getMessage().getFrom();
//            name = sender.getFirstName();
//        Message message = update.getMessage();
//     String text =  message.getText();
//        long id = sender.getId();
//        System.out.println(id);
//        System.out.println(text);
        try {
            execute(new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(getOutputMessage()));
        generalService.saveUsersAndMessages(update);
            usersService.saveUserToBase(update.getMessage().getFrom(), update);
//            usersService.updateUserToBase(update.getMessage().getFrom(), update);
            System.out.println();

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
