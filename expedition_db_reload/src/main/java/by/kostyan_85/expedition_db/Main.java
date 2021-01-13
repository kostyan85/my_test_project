package by.kostyan_85.expedition_db;

import by.kostyan_85.expedition_db.carrier.CarrierDaoImpl;
import by.kostyan_85.expedition_db.swing.FrameTitle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Created by Zver on 23.02.2020.
 */

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(FrameTitle.class);
    }

}


