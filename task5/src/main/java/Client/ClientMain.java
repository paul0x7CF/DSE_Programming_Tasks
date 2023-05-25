package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);

    public static void main(String[] args) {
        ClientProxy logStorage = new ClientProxy();

        try {
            System.out.println("Use Case 1");
            for (int i = 0; i < 100; i++) {
                logStorage.singleLog("Test " + i);
                ;
            }
        } catch (Exception e) {
            logger.error("Error while writing to log");
        }

        try {
            System.out.println("Use Case 2");
            final int REMOVE_BY = 100;
            logStorage.removeOldLogs(REMOVE_BY);
            for (int i = 0; i < 50; i++) {
                logStorage.singleLog("Test " + i);
            }
        } catch (Exception e) {
            logger.error("Error while deleting logs");
        }


        System.out.println("Use Case 3");
        String[] logEntries = new String[100];
        Arrays.fill(logEntries, "BulkTestLogs");
        CallbackIncLogStorage callback = new CallbackIncLogStorage() {
            @Override
            public void success() {
                logger.info("Successfully increased storage space");
            }

            @Override
            public void failure() {
                logger.info("Failed to increase storage space");
            }
        };
        logStorage.increaseStorageSpace(logEntries.length, callback);
        String[] compressedData = CompressData.compress(logEntries);

        //logStorage.addLogsInBulk(compressedData);




    }
}
