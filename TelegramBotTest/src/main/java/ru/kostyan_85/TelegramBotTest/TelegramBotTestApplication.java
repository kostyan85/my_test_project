package ru.kostyan_85.TelegramBotTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@SpringBootApplication
public class TelegramBotTestApplication {

    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
//        SpringApplication.run(TelegramBotTestApplication.class, args);
        SpringApplication.run(TelegramBotTestApplication.class,args);

    }

}
