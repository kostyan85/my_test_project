package ru.kostyan_85.TelegramBotTest.Repository;


import org.springframework.data.jpa.repository.Query;
import ru.kostyan_85.TelegramBotTest.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {

    /**
     * проверка на наличие пользователеля в БД
     * */
    @Query("select case " +
            "when count (u) > 0 then true " +
            "else false " +
            "end " +
            "from Users u " +
            "where u.userTelegramId = ?1")
    Boolean hasUserById(Long userTelegramId);


/**
 * поиск пользователя по telegramId
 * */
    Optional<Users> findByUserTelegramId(Long userTelegramId);

    /**
     * получение всех userTelegramId для рассылки
     * */
    @Query("select userTelegramId from Users")
    ArrayList findAllByUserTelegramId();

}
