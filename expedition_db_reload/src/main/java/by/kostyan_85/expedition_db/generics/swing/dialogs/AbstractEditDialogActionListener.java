package by.kostyan_85.expedition_db.generics.swing.dialogs;

import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;

import javax.swing.*;

public abstract class AbstractEditDialogActionListener<T extends Entity> extends AbstractDialogActionListener<T> {

    protected JFrame frame;
    protected JTable table;

    public AbstractEditDialogActionListener(Service<T> service, JFrame frame, JTable table) {
        super(service);
        this.frame = frame;
        this.table = table;
    }
}
