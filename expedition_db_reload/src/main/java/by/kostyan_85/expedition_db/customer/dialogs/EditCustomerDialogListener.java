package by.kostyan_85.expedition_db.customer.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.carrier.CarrierDTO;
import by.kostyan_85.expedition_db.carrier.CarrierDialog;
import by.kostyan_85.expedition_db.customer.Customer;
import by.kostyan_85.expedition_db.customer.CustomerDTO;
import by.kostyan_85.expedition_db.customer.CustomerDialog;
import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractEditDialogActionListener;
import by.kostyan_85.expedition_db.swing.FrameUtils;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialogCallback;
import by.kostyan_85.expedition_db.swing.dialog.DialogFrameFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Zver on 27.04.2020.
 */
public class EditCustomerDialogListener<T extends Entity> extends AbstractEditDialogActionListener<Customer> {

    public EditCustomerDialogListener(Service<Customer> service, JFrame frame, JTable table) {
        super(service, frame, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO check it works correctly
        if (table.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(null, "Не выбран элемент", "Ошибка",
                    JOptionPane.OK_OPTION);
        } else {
            FrameUtils.disableFrame(frame);
            // FIXME !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            Customer customer = (Customer) service.get(table.getSelectedRow());
            // TODO вынести в константы l10n
            JFrame editDialogFrame = DialogFrameFactory.create("Редактирование Заказчика");
            editDialogFrame.add(new CustomerDialog(new CustomerDTO(customer)).createContents(new AbstractDialogCallback<CustomerDTO>() {
                @Override
                public void onConfirm(CustomerDTO customerDTO) {
                    if (service.isUnique((Customer) customerDTO.toEntity())) {
                       Customer customer = new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getContacts(), customerDTO.getComments());
                        service.update(customer);
                    } else
                        JOptionPane.showMessageDialog(null, "Заказчик с такими контактами уже существует", "Ошибка",
                                JOptionPane.OK_OPTION);
                    // TODO check if it needed at all
                    FrameUtils.switchFrames(editDialogFrame, frame);
                }

                @Override
                public void onCancel() {
                    FrameUtils.switchFrames(editDialogFrame, frame);
                }
            }));
        }
    }
}
