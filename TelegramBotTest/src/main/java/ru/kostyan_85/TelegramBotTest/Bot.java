package ru.kostyan_85.TelegramBotTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kostyan_85.TelegramBotTest.Services.DailyDomainsService;
import ru.kostyan_85.TelegramBotTest.Services.GeneralService;
import ru.kostyan_85.TelegramBotTest.Services.TaskTimerService;
import ru.kostyan_85.TelegramBotTest.Services.UsersService;

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

    public void sendMsg(Message message, String text){
        SendMessage sendMessage = new SendMessage();            //создали объект класса, то бишь проинициализировали отправленное сообщение
        sendMessage.enableMarkdown(true);                            //включили возможность разметки
        sendMessage.setChatId(message.getChatId().toString());      //определяем ID чата, чтобы знать на какой конкретно чат нужно отправить ответ
        sendMessage.setReplyToMessageId(message.getMessageId());    //определяем ID сообщения, чтобы знать на какое ответить
        sendMessage.setText(text);
        s//установить сообщению текст, который отправили в метод
        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onUpdateReceived(Update update) {
taskTimerService.timerTask();


        try {
            execute(new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(getOutputMessage()));

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
