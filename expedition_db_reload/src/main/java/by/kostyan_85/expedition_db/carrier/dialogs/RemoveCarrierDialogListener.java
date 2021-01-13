package by.kostyan_85.expedition_db.carrier.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractRemoveDialogActionListener;
import by.kostyan_85.expedition_db.swing.RemoveConfirmation;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RemoveCarrierDialogListener extends AbstractRemoveDialogActionListener<Carrier> {

    public RemoveCarrierDialogListener(Service<Carrier> service, JTable table) {
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
//                            CarrierService.getInstance().remove((Integer) tableCarrier.getModel().getValueAt(tableCarrier.getSelectedRow(), 0));
                    service.delete((Integer) table.getModel().getValueAt(table.getSelectedRow(), 0));

//                            CarrierService.getInstance().removeFromTable(FrameCarriers.tableCarrier.getSelectedRow());
                    // TODO redraw recheck
//                    service.removeFromTable(FrameCarriers.table.getSelectedRow());

                    // TODO
//                    carrierModel.fireTableChanged(null);
                }
            });
        }
    }
}
