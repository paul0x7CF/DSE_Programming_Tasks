package Server;

import Client.MainClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainServer {

    private static final Logger logger = LogManager.getLogger(MainServer.class);
    public static void main(String[] args) {

        System.out.println("Hello world!");
        logger.debug("Hello world!");
    }
}
