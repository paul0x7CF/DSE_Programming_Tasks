package Client;

import KickStartDev.LogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);
    public static void main(String[] args) {
        ClientProxy logStorage = new ClientProxy();

        try {
            logger.info("Write to log");
            for (int i = 0; i < 100; i++) {
                logStorage.singleLog("Test " + i);;
            }
            final int REMOVE_BY = 50;
            logStorage.removeOldLogs(REMOVE_BY);
        } catch (Exception e) {
            logger.error("Error while writing to log {}", e);
        }


    }
}
