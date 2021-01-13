import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Zver on 14.12.2019.
 */
public class CreateFrame {

    public  static void startFrame() {
        JFrame frame = new JFrame("My First Panel");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new GridBagLayout());

        JButton button = new JButton("RUN");
        ActionListener actionListener = new ButtonEventListener();
        button.addActionListener(actionListener);
        button.setPreferredSize(new Dimension(100, 100));
        button.setBackground(Color.BLUE);
        button.setForeground(Color.CYAN);

        frame.add(button);

        frame.setVisible(true);

    }

    static class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run() {
                    ZipClass.createZip();
                    System.out.println(" поток закончен");
                }
            });
            System.out.println(thread.getName() + " поток запущен");
            thread.start();

        }
    }
}
