package TelegramBot.Services;

import TelegramBot.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Service
public class TaskTimerService {
    @Autowired
    private Bot bot;
    @Autowired
    private DailyDomainsService dailyDomainsService;


    @Scheduled(cron = "${settings.cron}")
    public void timerTask() {
        try {
            System.out.println("старт таймер");
            bot.sendMsg(dailyDomainsService.formMessageForUser());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
