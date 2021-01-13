package by.kostyan_85.expedition_db.customer;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialog;
import javax.swing.*;

/**
 * Created by Zver on 24.02.2020.
 */
public class CustomerDialog extends AbstractDialog<CustomerDTO> {
    private int id;
    private JTextField nameField, contactField, commentField;

    public CustomerDialog() {
        super();

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

    public CustomerDialog(CustomerDTO customer) {
        this();
        id = customer.getId();
        nameField.setText(customer.getName());
        contactField.setText(customer.getContacts());
        commentField.setText(customer.getComments());
    }

    @Override
    public CustomerDTO createDTO() {
        return new CustomerDTO(id ,nameField.getText(), contactField.getText(), commentField.getText());
    }

}
