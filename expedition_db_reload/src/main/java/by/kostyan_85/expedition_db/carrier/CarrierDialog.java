package by.kostyan_85.expedition_db.carrier;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialog;
import javax.swing.*;

/**
 * Created by Zver on 03.03.2020.
 */
public class CarrierDialog extends AbstractDialog<CarrierDTO> {

    private JTextField  nameField, contactField, commentField;
    private int id;

    public CarrierDialog() {
        super();

//        idField =new JTextField("Введите ID",10);
//        idField.setHorizontalAlignment(JTextField.RIGHT);
        // TODO вынести в константы l10n
        nameField = new JTextField("Введите название", 25);
        nameField.setHorizontalAlignment(JTextField.RIGHT);

        // TODO вынести в константы l10n
        contactField = new JTextField("Введите контактные данные", 25);
        contactField.setHorizontalAlignment(JTextField.RIGHT);

        // TODO вынести в константы l10n
        commentField = new JTextField("Ведите коментарий", 25);
        commentField.setHorizontalAlignment(JTextField.RIGHT);

        addComponents(nameField, contactField, commentField);
    }

    public CarrierDialog(CarrierDTO carrier) {
        this();
        id = carrier.getId();
        nameField.setText(carrier.getName());
        contactField.setText(carrier.getContacts());
        commentField.setText(carrier.getComments());
    }

    @Override
    public CarrierDTO createDTO() {
        return new CarrierDTO(id, nameField.getText(), contactField.getText(), commentField.getText());
    }

}
