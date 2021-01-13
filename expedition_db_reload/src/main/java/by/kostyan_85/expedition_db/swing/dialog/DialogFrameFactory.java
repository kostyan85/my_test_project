package by.kostyan_85.expedition_db.swing.dialog;

import javax.swing.*;

public class DialogFrameFactory {

    private DialogFrameFactory() {}

    public static JFrame create(String title) {
        final JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setResizable(false);
        frame.setVisible(true);
        return frame;
    }

}
