package by.kostyan_85.expedition_db.generics.swing.dialogs;

import by.kostyan_85.expedition_db.generics.Entity;
import by.kostyan_85.expedition_db.generics.Service;

import java.awt.event.ActionListener;

public abstract class AbstractDialogActionListener<T extends Entity> implements ActionListener {

    protected Service<T> service;

    public AbstractDialogActionListener(Service<T> service) {
        this.service = service;
    }
}
