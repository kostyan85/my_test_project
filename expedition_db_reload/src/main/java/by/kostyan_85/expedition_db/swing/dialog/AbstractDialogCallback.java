package by.kostyan_85.expedition_db.swing.dialog;

import by.kostyan_85.expedition_db.generics.swing.AbstractDTO;

import java.sql.SQLException;

public abstract class AbstractDialogCallback<T extends AbstractDTO> {

    public abstract void onConfirm(T dto) throws SQLException, ClassNotFoundException;
    public abstract void onCancel();

}
