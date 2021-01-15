package by.kostyan_85.expedition_db.carrier;

import by.kostyan_85.expedition_db.carrier.dialogs.AddCarrierDialogListener;
import by.kostyan_85.expedition_db.carrier.dialogs.EditCarrierDialogListener;
import by.kostyan_85.expedition_db.carrier.dialogs.RemoveCarrierDialogListener;
import by.kostyan_85.expedition_db.generics.swing.AbstractTableManager;
import by.kostyan_85.expedition_db.generics.swing.Facade.CarrierFacade;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Created by Zver on 02.03.2020.
 */
// TODO move logic into abstract parent
public class FrameCarriers extends AbstractTableManager<Carrier> {
    private CarrierService service;
    private CarrierFacade facade;
    // TODO think about JFrame decorator with table inside
    private JTable carrierTable;
    private JFrame frame;

    public FrameCarriers(/*CarrierFacade facade,*/CarrierService service, CarrierTableModel carrierModel) {
        this.facade = facade;
        this.service = service;
        this.carrierTable = createTable(carrierModel);
        this.frame = createFrame("Перевозчики");
    }

    public void paintFrame() {
        frame.add(createTablePanel(), BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private JTable createTable(TableModel tableModel) {
        JTable table = new JTable(tableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        frame.setPreferredSize(new Dimension(screenWidth, 600));
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        return frame;
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
        tablePanel.add(new JScrollPane(carrierTable));
        return tablePanel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel();
        JButton[] buttons = createPanelButtons();
        for (JButton button : buttons) {
            button.setSize(150, 50);
            buttonPanel.add(button);
        }
        return buttonPanel;
    }

    private JButton[] createPanelButtons() {
        return new JButton[]{createAddButton(), createEditButton(), createRemoveButton()};
    }

    private JButton createAddButton() {
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new AddCarrierDialogListener(frame, service));
        return addButton;
    }

    private JButton createEditButton() {
        JButton editButton = new JButton("Редактировать");
        editButton.addActionListener(new EditCarrierDialogListener(service, frame, carrierTable));
        return editButton;
    }

    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new RemoveCarrierDialogListener(service, carrierTable));
        return removeButton;
    }

}
