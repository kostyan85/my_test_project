package ru.kostyan_85.TelegramBotTest.Entity;

import javax.persistence.*;
import java.util.List;


@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userTelegramId;

    private String userName;

    @Column(name = "last_message_a")
    private String lastMessageAt;


    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
//    @JoinColumn(name = "users_id")
    private List<Messages> messages;

    public Users() {
    }



    public void setLastMessageAt(String lastMessageAt) {
        this.lastMessageAt = lastMessageAt;
    }

    public String getLastMessageAt() {
        return lastMessageAt;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public Long getUserTelegramId() {
        return userTelegramId;
    }

    public void setUserTelegramId(Long userId) {
        this.userTelegramId = userId;
    }
}
