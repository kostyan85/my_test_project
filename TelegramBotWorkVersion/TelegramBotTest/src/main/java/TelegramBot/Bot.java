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
    private GeneralService generalService;

    @Autowired
    private UsersService usersService;

    private String outputMessage = "Пользователь зарегестрирован, сообщения сохранены";

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
     */
    public void sendMsg(String text) throws TelegramApiException {

        ArrayList listUsers = usersService.getAllUserTelegramId();
        if (listUsers != null) {
            for (int i = 0; i < listUsers.size(); i++) {
                SendMessage sendMessage = new SendMessage();
                sendMessage.enableMarkdown(true);
                sendMessage.setChatId(String.valueOf(listUsers.get(i)));
                sendMessage.setText(text);
                execute(sendMessage);
            }
        } else LOGGER.error("sendMsg: listUsers = null");
    }

    /**
     * принимаем сообщение пользователя
     * регистрируем или обновляем пользователя
     * отправляем ответное сообщение с приветствием
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(new SendMessage().setChatId(update.getMessage().getChatId())
                    .setText(getOutputMessage()));
            generalService.saveUsersAndMessages(update);
        } catch (TelegramApiException e) {
            LOGGER.error("onUpdateReceived : {0}", e);
            e.printStackTrace();
        }
    }
}
