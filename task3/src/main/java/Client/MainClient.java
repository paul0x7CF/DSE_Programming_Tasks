package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainClient {

    private static final Logger logger = LogManager.getLogger(MainClient.class);
    public static void main(String[] args) {

        System.out.println("Hello world!");
        logger.debug("Hello world!");
    }
}