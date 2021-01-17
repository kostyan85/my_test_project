package ru.kostyan_85.TelegramBotTest.Repository;

import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kostyan_85.TelegramBotTest.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {
    @Query("select case " +
            "when count (u) > 0 then true " +
            "else false " +
            "end " +
            "from Users u " +
            "where u.userTelegramId = ?1")
    Boolean hasUserById(Long userTelegramId);



    Optional<Users> findByUserTelegramId(Long userTelegramId);


}
