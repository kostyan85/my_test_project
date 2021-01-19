package ru.kostyan_85.TelegramBotTest.Entity;

import javax.persistence.*;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   private String inMessage;

   private String outMessage;

   @ManyToOne(cascade = CascadeType.MERGE,optional = false)
   private Users users;

    public Messages() {
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInMessage() {
        return inMessage;
    }

    public void setInMessage(String inMessage) {
        this.inMessage = inMessage;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }
}
