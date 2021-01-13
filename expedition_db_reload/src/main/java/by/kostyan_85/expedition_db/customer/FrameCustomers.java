package by.kostyan_85.expedition_db.customer;


import by.kostyan_85.expedition_db.carrier.dialogs.EditCarrierDialogListener;
import by.kostyan_85.expedition_db.customer.dialogs.AddCustomerDialogListener;
import by.kostyan_85.expedition_db.customer.dialogs.EditCustomerDialogListener;
import by.kostyan_85.expedition_db.customer.dialogs.RemoveCustomerDialogListener;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;


/**
 * Created by Zver on 23.02.2020.
 */
public class FrameCustomers {

    private CustomerService service;
    // TODO think about JFrame decorator with table inside
    private JTable customerTable;
    private JFrame frame;

    public FrameCustomers(CustomerService service, CustomerTableModel customerModel) {
        this.service = service;
        this.customerTable = createTable(customerModel);
        this.frame = createFrame("Заказчики");
    }

    public void paintFrame() {
        frame.add(createTablePanel(), BorderLayout.CENTER);
        frame.add(createButtonPanel(), BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
    }

    private JPanel createTablePanel() {
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
        tablePanel.add(new JScrollPane(customerTable));
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

    private JButton createAddButton() {
        JButton addButton = new JButton("Добавить");
        addButton.addActionListener(new AddCustomerDialogListener(frame, service));
        return addButton;
    }

    private JButton createEditButton() {
        JButton editButton = new JButton("Редактировать");
        editButton.addActionListener(new EditCustomerDialogListener(service, frame, customerTable));
        return editButton;
    }

    private JButton createRemoveButton() {
        JButton removeButton = new JButton("Удалить");
        removeButton.addActionListener(new RemoveCustomerDialogListener(service, customerTable));
        return removeButton;
    }

}
