package by.kostyan_85.expedition_db.carrier.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.carrier.CarrierDTO;
import by.kostyan_85.expedition_db.carrier.CarrierDialog;
import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractEditDialogActionListener;
import by.kostyan_85.expedition_db.swing.dialog.DialogFrameFactory;
import by.kostyan_85.expedition_db.swing.FrameUtils;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialogCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;

// TODO move to abstract parent
public class EditCarrierDialogListener<T extends Entity> extends AbstractEditDialogActionListener<Carrier> {

    public EditCarrierDialogListener(Service<Carrier> service, JFrame frame, JTable table) {
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
            Carrier carrier = (Carrier) service.get(table.getSelectedRow());
            // TODO вынести в константы l10n
            JFrame editDialogFrame = DialogFrameFactory.create("Редактирование Перевозчика");
            editDialogFrame.add(new CarrierDialog(new CarrierDTO(carrier)).createContents(new AbstractDialogCallback<CarrierDTO>() {
                @Override
                public void onConfirm(CarrierDTO carrierDTO) {
                    if (service.isUnique(carrierDTO.toEntity())) {
                        Carrier carrier = new Carrier(carrierDTO.getId(), carrierDTO.getName(), carrierDTO.getContacts(), carrierDTO.getComments());
                        service.update(carrier);
                    } else
                        JOptionPane.showMessageDialog(null, "Перевозчик с такими контактами уже существует", "Ошибка",
                                JOptionPane.OK_OPTION);
                    // TODO check if it needed at all
//                    carrierModel.fireTableChanged(null);
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
