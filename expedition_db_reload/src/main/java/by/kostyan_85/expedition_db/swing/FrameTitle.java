package by.kostyan_85.expedition_db.swing;
import by.kostyan_85.expedition_db.carrier.FrameCarriers;
import by.kostyan_85.expedition_db.customer.FrameCustomers;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by Zver on 02.03.2020.
 */
// TODO refactor into smaller functions
public class FrameTitle {
   private FrameCarriers frameCarriers;
   private FrameCustomers frameCustomers;
    JFrame titleFrame;
    JPanel buttonPanel;
    JPanel imgPanel;
    JLabel imgLabel;
    private static JButton customersDB, carrirsDB, voyageDB, taxes;

    public FrameTitle(FrameCustomers frameCustomers, FrameCarriers frameCarriers) throws IOException {
        this.frameCustomers = frameCustomers;
        this.frameCarriers = frameCarriers;


        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        titleFrame = new JFrame();
        titleFrame.setTitle("Главная страничка");
        titleFrame.setLayout(new BorderLayout());
        titleFrame.setSize(600, 450);
        titleFrame.setResizable(false);
        titleFrame.setBackground(Color.DARK_GRAY);
        titleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titleFrame.setLocation(screenWidth/2-300,screenHeight/2-225);

        buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        customersDB = new JButton("База заказчиков");
        customersDB.setSize(100, 50);
        customersDB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameCustomers.paintFrame();
//                FrameCustomers.paintFrame();
            }
        });

        carrirsDB = new JButton("База перевозчиков");
        carrirsDB.setSize(100, 50);
        carrirsDB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameCarriers.paintFrame();
//                FrameCarriers.paintFrame();

            }
        });

        voyageDB = new JButton("База перевозок");
        voyageDB.setSize(100, 50);
        voyageDB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        taxes = new JButton("Налоги");
        taxes.setSize(100, 50);
        taxes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        String imagePath = "scaniaImg1.jpg";
        BufferedImage myPicture = ImageIO.read(new File(imagePath));
        imgLabel = new JLabel(new ImageIcon(myPicture));
        imgPanel = new JPanel();
        imgPanel.add(imgLabel);
        imgPanel.setBackground(Color.DARK_GRAY);


        buttonPanel.add(customersDB);
        buttonPanel.add(carrirsDB);
        buttonPanel.add(voyageDB);
        buttonPanel.add(taxes);

        titleFrame.add(buttonPanel, BorderLayout.NORTH);
        titleFrame.add(imgPanel, BorderLayout.SOUTH);

        titleFrame.pack();
        titleFrame.setVisible(true);
    }


}
