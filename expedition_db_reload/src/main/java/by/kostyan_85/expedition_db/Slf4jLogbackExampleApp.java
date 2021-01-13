package by.kostyan_85.expedition_db;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by Zver on 30.04.2020.
 */
public class Slf4jLogbackExampleApp {

        private static final Logger logger = LoggerFactory.getLogger(
                Slf4jLogbackExampleApp.class);

        private static final String FILENAME = "/file/does/not/exist";

        public static void main(String[] args) {
            logger.info("Just a log message.");
            logger.debug("Message for debug level.");
            try {
                Files.readAllBytes(Paths.get(FILENAME));
            } catch (IOException ioex) {
                logger.error("Failed to read file {}.", FILENAME, ioex);
            }
        }
    }

