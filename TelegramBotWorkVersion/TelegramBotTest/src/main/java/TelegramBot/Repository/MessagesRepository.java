package TelegramBot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import TelegramBot.Entity.Messages;

public interface MessagesRepository extends JpaRepository<Messages,Long> {
}
