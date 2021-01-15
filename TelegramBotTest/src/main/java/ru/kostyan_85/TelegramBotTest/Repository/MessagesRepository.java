package ru.kostyan_85.TelegramBotTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kostyan_85.TelegramBotTest.Entity.Messages;

public interface MessagesRepository extends JpaRepository<Messages,Long> {
}
