package by.kostyan_85.expedition_db.swing.dialog;

import by.kostyan_85.expedition_db.generics.swing.AbstractDTO;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDialog<T extends AbstractDTO> {
    private int id;
    private List<JComponent> components;

    protected AbstractDialog() {
        components = new ArrayList<>();
    }

    public JPanel createContents(final AbstractDialogCallback<T> callback) {
        JPanel contents = new JPanel(new FlowLayout(FlowLayout.LEADING));
        for (JComponent comp : components) {
            contents.add(comp);
        }
        JButton confirmButton = new JButton();
        confirmButton.addActionListener(e -> {
            try {
                callback.onConfirm(createDTO());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        // TODO вынести в константы l10n
        confirmButton.setText("Сохранить");

        JButton cancelButton = new JButton();
        cancelButton.addActionListener(e -> callback.onCancel());
        // TODO вынести в константы l10n
        cancelButton.setText("Отмена");
        contents.add(confirmButton);
        contents.add(cancelButton);
        return contents;
    }

    protected void addComponents(JComponent... els) {
        for (JComponent el : els) {
            components.add(el);
        }
    }

    public abstract T createDTO();

}
