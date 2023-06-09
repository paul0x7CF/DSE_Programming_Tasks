package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;


public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);

    public static void main(String[] args) {
        ClientProxy logStorage = new ClientProxy();


        /*try {
            System.out.println("Use Case 1");
            for (int i = 0; i < 100; i++) {
                logStorage.singleLog("Test " + i);
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
        }*/


       /* System.out.println("Use Case 3");
        String[] logEntries = new String[100];
        Arrays.fill(logEntries, "BulkTestLogs");
        final String[][] compressedHolder = new String[][]{new String[1]};
        CallbackIncLogStorage callbackOnInc = isIncBy -> {
            logger.info("Successfully increased storage space By {}", isIncBy);
            logger.debug("Adding logs in bulk");
            CallbackOnCompressed callbackOnCompressed = isCompressed -> {
                logger.info("Successfully added logs in bulk");
                logStorage.addLogsInBulk(isCompressed);
            };
            logStorage.addLogsInBulk(compressedHolder[0]);

        };
        
        logStorage.increaseStorageSpace(logEntries.length, callbackOnInc);
        String[] compressedData = CompressData.compress(logEntries);

        compressedHolder[0] = CompressData.compress(logEntries);
        logger.debug("Do something");

        //logStorage.addLogsInBulk(compressedData);*/

        System.out.println("Use Case 4");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine();
        scanner.close();
        System.out.println(searchTerm);

        PollSearch pollSearch = logStorage.searchLogs(searchTerm);




    }
}
