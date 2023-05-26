package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;


public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);

    public static void main(String[] args) {
        ClientProxy logStorage = new ClientProxy();
        UDPClientRequestHandlerIn udpClientRequestHandlerIn = new UDPClientRequestHandlerIn();
        new Thread(udpClientRequestHandlerIn).start();

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
            public void callback(boolean isSuccessful) {
                logger.info("Successfully increased storage space");
            }

        };
        logStorage.increaseStorageSpace(logEntries.length, callback);
        String[] compressedData = CompressData.compress(logEntries);
        logger.debug("Do something");

        //logStorage.addLogsInBulk(compressedData);




    }
}
