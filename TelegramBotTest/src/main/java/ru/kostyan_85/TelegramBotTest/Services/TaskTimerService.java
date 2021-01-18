package ru.kostyan_85.TelegramBotTest.Services;



import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kostyan_85.TelegramBotTest.Bot;


import java.util.Timer;
import java.util.TimerTask;

@Component
public class TaskTimerService  {
    @Autowired
    Bot bot;
    @Autowired
    DailyDomainsService dailyDomainsService;
    /**
     * создаем таймер для ежедневной рассылки
     * */

    @Scheduled(cron = "0 0 12 * * *")
    public void timerTask() {
        try {
            System.out.println("старт таймер");
            bot.sendMsg(dailyDomainsService.formMessageForUser());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
//        dailyDomainsService.formMessageForUser();
    }
//        System.out.println("таймер запущен");
//        TimerTask repeatedTask = new TimerTask() {
//            public void run() {
//                dailyDomainsService.formMessageForUser();
//
//            }
//        };
//        Timer timer = new Timer("Timer");
//
//        long delay = 1000L;
//        long period = 1000L *  60L *  60L *  24L;
//        timer.scheduleAtFixedRate(repeatedTask, delay, period);
//    }


}
