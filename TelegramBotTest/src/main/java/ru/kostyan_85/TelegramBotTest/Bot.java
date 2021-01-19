package ru.kostyan_85.TelegramBotTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kostyan_85.TelegramBotTest.Services.DailyDomainsService;
import ru.kostyan_85.TelegramBotTest.Services.GeneralService;
import ru.kostyan_85.TelegramBotTest.Services.TaskTimerService;
import ru.kostyan_85.TelegramBotTest.Services.UsersService;

import java.util.ArrayList;

@Component
public class Bot extends TelegramLongPollingBot {
    @Autowired
    TaskTimerService taskTimerService;
    @Autowired
    GeneralService generalService;
    @Autowired
    DailyDomainsService dailyDomainsService;
    @Autowired
    private UsersService usersService;

    private String outputMessage = "Приветствую тебя пользователь";

    public String getOutputMessage() {
        return outputMessage;
    }

    public void setOutputMessage(String outputMessage) {
        this.outputMessage = outputMessage;
    }


    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;

    /**
     * получаем имя бота
     */
    @Override
    public String getBotUsername() {
        return botName;
    }

    /**
     * получаем токен бота
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * отправляем рассылку пользователям которые зарегестрированы в БД
     * циклом проходим по массиву пользователей
     */
    public void sendMsg(/*Message message, */String text) throws TelegramApiException {
        System.out.println("старт sendMsg");
        ArrayList listUsers = usersService.getAllUserTelegramId();
        for (int i = 0; i < listUsers.size(); i++) {
            SendMessage sendMessage = new SendMessage();                 //создали объект класса, то бишь проинициализировали отправленное сообщение
            sendMessage.enableMarkdown(true);                            //включили возможность разметки
            sendMessage.setChatId(String.valueOf(listUsers.get(i)));      //определяем ID чата, чтобы знать на какой конкретно чат нужно отправить ответ
            sendMessage.setText(text);                                       //установить сообщению текст, который отправили в метод
            execute(sendMessage);
        }
    }

    /**
     * принимаем сообщение пользователя
     * регистрируем или обновляем пользователя
     * отправляем ответное сообщение с приветствием
     */
    @Override
    public void onUpdateReceived(Update update) {
//        //TODO для отправки рассылки
//        Message message = update.getMessage();
//        System.out.println("запуск");
//        try {
//            sendMsg(dailyDomainsService.formMessageForUser());
//
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }

//TODO для добавления новых пользователей

        try {
            execute(new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(getOutputMessage()));
            generalService.saveUsersAndMessages(update);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
