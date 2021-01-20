package TelegramBot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import TelegramBot.Services.DailyDomainsService;
import TelegramBot.Services.GeneralService;
import TelegramBot.Services.TaskTimerService;
import TelegramBot.Services.UsersService;

import java.util.ArrayList;

@Component
public class Bot extends TelegramLongPollingBot {
    private static final Logger LOGGER = LoggerFactory.getLogger(Bot.class);
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

//    /**
//     * создаем таймер для ежедневной рассылки
//     *
//     * @Scheduled(cron = "0 0 12 * * *") настройка на 12 часов каждого дня
//     */
//    @Scheduled(cron = "${settings.cron}")
//    public void timerTask() {
//        try {
//            System.out.println("старт таймер");
//            sendMsg(dailyDomainsService.formMessageForUser());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * отправляем рассылку пользователям которые зарегестрированы в БД
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
            LOGGER.error("трям трям");
            execute(new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(getOutputMessage()));
            generalService.saveUsersAndMessages(update);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
