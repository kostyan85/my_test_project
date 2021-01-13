package by.kostyan_85.expedition_db.swing;

import javax.swing.*;

public class RemoveConfirmation {

    private static final int YES = 0;

    private RemoveConfirmation() {
    }

    public static void showError() {
        // TODO вынести в константы l10n
        JOptionPane.showMessageDialog(null, "Не выбран элемент", "Ошибка",
                JOptionPane.OK_OPTION);
    }

    public static void showConfirmation(Callback callback) {
        // TODO вынести в константы l10n
        int selectResult = JOptionPane.showConfirmDialog(null,
                "Вы действительно хотите удалить этот элемент?",
                "Удаление элемента?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (selectResult == YES) {
            callback.onConfirm();
        }
    }

    public static abstract class Callback {
        public abstract void onConfirm();
    }

}
