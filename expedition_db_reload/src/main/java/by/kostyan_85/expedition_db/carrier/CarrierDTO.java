package by.kostyan_85.expedition_db.carrier;
import by.kostyan_85.expedition_db.generics.swing.AbstractDTO;


/**
 * Created by Zver on 02.03.2020.
 */
public class CarrierDTO extends AbstractDTO<Carrier> {

    private String name;
    private String contacts;
    private String comments;

    public CarrierDTO(int id, String name, String contacts, String comments) {
        super(id);
        this.name = name;
        this.contacts = contacts;
        this.comments = comments;
    }

    public CarrierDTO(Carrier carrier) {
        this(carrier.getId(),carrier.getName(), carrier.getContacts(), carrier.getComments());
    }

    @Override
    public Carrier toEntity() {
        return new Carrier(getId(), name, contacts, comments);
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
