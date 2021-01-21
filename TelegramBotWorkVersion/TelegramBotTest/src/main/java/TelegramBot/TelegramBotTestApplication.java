package TelegramBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@EnableScheduling
@SpringBootApplication
public class TelegramBotTestApplication {

    public static void main(String[] args) throws TelegramApiRequestException {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramBotTestApplication.class,args);

    }

}
