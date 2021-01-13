package by.kostyan_85.expedition_db.swing;

import javax.swing.*;

public class FrameUtils {

    private FrameUtils() {}

    public static void disableFrame(JFrame frame) {
        frame.setEnabled(false);
    }

    public static void switchFrames(JFrame from, JFrame to) {
        // TODO убивать старый фрейм(!!!)
        from.setVisible(false);
        to.setEnabled(true);
        to.setVisible(true);
    }
}
