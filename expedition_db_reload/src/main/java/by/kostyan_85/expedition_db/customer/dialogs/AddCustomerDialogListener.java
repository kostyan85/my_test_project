package by.kostyan_85.expedition_db.customer.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.carrier.CarrierDTO;
import by.kostyan_85.expedition_db.carrier.CarrierDialog;
import by.kostyan_85.expedition_db.customer.Customer;
import by.kostyan_85.expedition_db.customer.CustomerDTO;
import by.kostyan_85.expedition_db.customer.CustomerDialog;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractAddDialogActionListener;
import by.kostyan_85.expedition_db.swing.FrameUtils;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialogCallback;
import by.kostyan_85.expedition_db.swing.dialog.DialogFrameFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by Zver on 27.04.2020.
 */
public class AddCustomerDialogListener extends AbstractAddDialogActionListener<Customer> {

    public AddCustomerDialogListener(JFrame frame, Service<Customer> service) {
        super(service, frame);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        FrameUtils.disableFrame(frame);
        // TODO вынести в константы l10n
        JFrame addDialog = DialogFrameFactory.create("Добавление Заказчика");
        addDialog.add(new CustomerDialog().createContents(new AbstractDialogCallback<CustomerDTO>() {
            @Override
            public void onConfirm(CustomerDTO customerDTO) {
                // TODO move to facade
                if (service.isUnique((Customer) customerDTO.toEntity())) {
                    service.save((Customer) customerDTO.toEntity());
                } else
                    JOptionPane.showMessageDialog(null, "Заказчик с такими контактами уже существует", "Ошибка",
                            JOptionPane.OK_OPTION);

                // TODO check if it still works
//                    carrierTable.getModel().fireTableChanged(null);

                FrameUtils.switchFrames(addDialog, frame);
            }

            @Override
            public void onCancel() {
                FrameUtils.switchFrames(addDialog, frame);
            }
        }));
    }
}
