package by.kostyan_85.expedition_db.generics.swing.dialogs;

import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;

import javax.swing.*;

public abstract class AbstractAddDialogActionListener<T extends Entity> extends AbstractDialogActionListener<T> {
    protected JFrame frame;

    public AbstractAddDialogActionListener(Service<T> service, JFrame frame) {
        super(service);
        this.frame = frame;
    }
}
