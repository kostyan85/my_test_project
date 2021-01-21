package TelegramBot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TelegramBot.Entity.Daily_domains;

public interface DailyDomainsRepository extends JpaRepository<Daily_domains,Long>{

}
