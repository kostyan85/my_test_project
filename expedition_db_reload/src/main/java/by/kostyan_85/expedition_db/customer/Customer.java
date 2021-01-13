package by.kostyan_85.expedition_db.customer;
import by.kostyan_85.expedition_db.generics.AbstractEntity;
import javax.persistence.*;



/**
 * Created by Zver on 23.02.2020.
 */
@Entity
@Table(name = "Заказчики")
public class Customer extends AbstractEntity {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "Название")
    private String name;

    @Column(name = "Контакты")
    private String contacts;

    @Column(name = "Коментарий")
    private String comments;

    public Customer(int id, String name, String contacts, String comments) {
        super(id);
        this.name = name;
        this.contacts = contacts;
        this.comments = comments;
    }
    public Customer() {
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


//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setContacts(String contacts) {
//        this.contacts = contacts;
//    }
//
//    public void setComments(String comments) {
//        this.comments = comments;
//    }
}
