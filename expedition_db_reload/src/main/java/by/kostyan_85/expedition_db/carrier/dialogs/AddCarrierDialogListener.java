package by.kostyan_85.expedition_db.carrier.dialogs;

import by.kostyan_85.expedition_db.carrier.Carrier;
import by.kostyan_85.expedition_db.carrier.CarrierDTO;
import by.kostyan_85.expedition_db.carrier.CarrierDialog;
import by.kostyan_85.expedition_db.generics.Service;
import by.kostyan_85.expedition_db.generics.swing.dialogs.AbstractAddDialogActionListener;
import by.kostyan_85.expedition_db.swing.dialog.DialogFrameFactory;
import by.kostyan_85.expedition_db.swing.FrameUtils;
import by.kostyan_85.expedition_db.swing.dialog.AbstractDialogCallback;

import javax.swing.*;
import java.awt.event.ActionEvent;

// TODO move to abstract parent
public class AddCarrierDialogListener extends AbstractAddDialogActionListener<Carrier> {

    public AddCarrierDialogListener(JFrame frame, Service<Carrier> service) {
        super(service, frame);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        FrameUtils.disableFrame(frame);
        // TODO вынести в константы l10n
        JFrame addDialog = DialogFrameFactory.create("Добавление Перевозчика");
        addDialog.add(new CarrierDialog().createContents(new AbstractDialogCallback<CarrierDTO>() {
            @Override
            public void onConfirm(CarrierDTO carrierDTO) {
                // TODO move to facade
                if (service.isUnique(carrierDTO.toEntity())) {
                    service.save(carrierDTO.toEntity());
                } else
                    JOptionPane.showMessageDialog(null, "Перевозчик с такими контактами уже существует", "Ошибка",
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
