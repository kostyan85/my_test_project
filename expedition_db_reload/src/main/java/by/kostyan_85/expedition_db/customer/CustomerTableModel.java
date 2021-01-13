package by.kostyan_85.expedition_db.customer;



import javax.swing.table.AbstractTableModel;

/**
 * Created by Zver on 27.04.2020.
 */
public class CustomerTableModel extends AbstractTableModel {
    private CustomerService service;

    public CustomerTableModel(CustomerService service) {
        this.service = service;
    }

    @Override
    public String getColumnName(int column) {
        return service.getColumnName(column);
    }

    @Override
    public int getRowCount() {
        return service.getData().length;
    }

    @Override
    public int getColumnCount() {
        return service.getColumnCount();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return service.getData()[rowIndex][columnIndex];
    }
}

