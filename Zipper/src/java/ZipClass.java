
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by Zver on 14.12.2019.
 */
public class ZipClass {
    public static Logger logger = Logger.getLogger(ZipClass.class);

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CreateFrame.startFrame();
            }
        });
    }


    public static void createZip() {

        File in = new File("resource" + File.separator + "Договор javamentor.docx");
        File out = new File("result" + File.separator + "Договор javamentor.zip");
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(out))) {
            try (FileInputStream fis = new FileInputStream(in)) {
                ZipEntry entry1 = new ZipEntry("Договор javamentor.docx");
                zout.putNextEntry(entry1);
                byte[] bytes = new byte[1024];
                int length;
                while ((length = fis.read(bytes)) >= 0) {
                    logger.info(logger.getClass() + " запись");
                    zout.write(bytes, 0, length);
                }
                fis.close();
                zout.closeEntry();
            }
        } catch (
                Exception ex)

        {

            System.out.println(ex.getMessage());
        }
    }

}
