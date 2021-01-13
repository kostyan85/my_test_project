package by.kostyan_85.expedition_db.carrier;

import by.kostyan_85.expedition_db.generics.AbstractEntity;

import javax.persistence.*;


/**
 * Created by Zver on 23.02.2020.
 */
@Entity
@Table(name = "Перевозчики")
public class Carrier extends AbstractEntity {
    @Id
    @GeneratedValue
    // TODO move to AbstractEntity
    private int id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Контакты")
    private String contacts;

    @Column(name = "Коментарий")
    private String comments;

    // TODO check if it really needed
    public Carrier() {
    }

    public Carrier(int id, String name, String contacts, String comments) {
        super(id);
        this.name = name;
        this.contacts = contacts;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public String getContacts() {
        return contacts;
    }

    public String getComments() {
        return comments;
    }
}
