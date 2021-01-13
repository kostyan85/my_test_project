package by.kostyan_85.expedition_db.carrier;

import javax.swing.table.AbstractTableModel;

/**
 * Created by Zver on 02.03.2020.
 */
public class CarrierTableModel extends AbstractTableModel {
    private CarrierService service;

    public CarrierTableModel(CarrierService service) {
        this.service = service;
    }

    @Override
    public String getColumnName(int column) {
        return service.getColumnName(column);
    }

    //TODO ата-та
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
