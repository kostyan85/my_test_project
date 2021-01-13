package by.kostyan_85.expedition_db.generics.swing.dialogs;

import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;

import javax.swing.*;

public abstract class AbstractRemoveDialogActionListener<T extends Entity> extends AbstractDialogActionListener<T> {

    protected JTable table;

    public AbstractRemoveDialogActionListener(Service<T> service, JTable table) {
        super(service);
        this.table = table;
    }
}
