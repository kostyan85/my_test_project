package ru.kostyan_85.TelegramBotTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kostyan_85.TelegramBotTest.Entity.Daily_domains;

public interface DailyDomainsRepository extends JpaRepository<Daily_domains,Long>{

}
