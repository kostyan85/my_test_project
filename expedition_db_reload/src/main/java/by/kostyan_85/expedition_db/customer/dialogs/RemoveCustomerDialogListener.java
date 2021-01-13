package by.kostyan_85.expedition_db.customer.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.customer.Customer;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractRemoveDialogActionListener;
import by.kostyan_85.expedition_db.swing.RemoveConfirmation;

import javax.swing.*;
import java.awt.event.ActionEvent;


/**
 * Created by Zver on 27.04.2020.
 */
public class RemoveCustomerDialogListener extends AbstractRemoveDialogActionListener<Customer> {

    public RemoveCustomerDialogListener(Service<Customer> service, JTable table) {
        super(service, table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!table.isRowSelected(table.getSelectedRow())) {
            RemoveConfirmation.showError();
        } else {
            RemoveConfirmation.showConfirmation(new RemoveConfirmation.Callback() {
                @Override
                public void onConfirm() {
                    service.delete((Integer) table.getModel().getValueAt(table.getSelectedRow(), 0));

                }
            });
        }
    }
}
