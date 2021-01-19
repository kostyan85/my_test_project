package ru.kostyan_85.TelegramBotTest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kostyan_85.TelegramBotTest.Bot;


@Service
public class TaskTimerService {
//    @Autowired
//    Bot bot;
//    @Autowired
//    DailyDomainsService dailyDomainsService;
//
//    /**
//     * создаем таймер для ежедневной рассылки
//     * @Scheduled(cron = "0 0 12 * * *") настройка на 12 часов каждого дня
//     */
//
////    @Scheduled(cron = "0 0 12 * * *")
////    @Scheduled(cron = "0 0/2 * * * *")
//    //TODO почему в настройках ругается?
//    @Scheduled(cron = "${settings.cron}")
//    public void timerTask() {
//        try {
//            System.out.println("старт таймер");
//            bot.sendMsg(dailyDomainsService.formMessageForUser());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
//    }
}
