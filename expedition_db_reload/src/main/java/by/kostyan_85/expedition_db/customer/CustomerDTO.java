package by.kostyan_85.expedition_db.customer;
import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.swing.AbstractDTO;


/**
 * Created by Zver on 25.02.2020.
 */
public class CustomerDTO extends AbstractDTO {

    private String name;
    private String contacts;
    private String comments;

    public CustomerDTO(int id,String name, String contacts, String comments) {
         super(id);
        this.name = name;
        this.contacts = contacts;
        this.comments = comments;
    }

    public CustomerDTO(Customer customer) {
        this( customer.getId(),customer.getName(), customer.getContacts(), customer.getComments());
    }

    @Override
    public Entity toEntity() {
        return new Customer(getId(), name, contacts, comments);
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
