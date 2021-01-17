package ru.kostyan_85.TelegramBotTest.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class TaskTimerService {

    @Autowired
    DailyDomainsService dailyDomainsService;
    public void timerTask() {
        System.out.println("таймер запущен");
        TimerTask repeatedTask = new TimerTask() {
            public void run() {
                dailyDomainsService.formMessageForUser();
//                System.out.println("Task performed on " + new Date());
            }
        };
        Timer timer = new Timer("Timer");

        long delay = 1000L;
        long period = 1000L *  60L *  60L *  24L;
        timer.scheduleAtFixedRate(repeatedTask, delay, period);
    }
}
