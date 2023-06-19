package Client;

import Shared.LogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Scanner;


public class ClientMain {
    private static final Logger logger = LogManager.getLogger(ClientMain.class);

    public static void main(String[] args) {
        ClientProxy logStorage = new ClientProxy();


        try {
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
        }

        System.out.println("Use Case 3");
        boolean executed = false;
        LogEntry[] logEntries = new LogEntry[500];
        PollLogStorage pollLogStorage= logStorage.increaseStorageSpace(logEntries.length);
        Arrays.fill(logEntries, new LogEntry("Bulk Test"));
        LogEntry[] compressedData = CompressData.compress(logEntries);
        executed = checkPoll(pollLogStorage, compressedData, logStorage);


        System.out.println("Use Case 4");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter search term:");
        String searchTerm = scanner.nextLine();
        scanner.close();
        CallbackSearch callbackOnSearch = searchResults -> {
            logger.info("Successfully found {} Search Results", searchResults.length);
            for (LogEntry result : searchResults) {
                System.out.println(result);
            }

        };

        logStorage.searchLogs(searchTerm, callbackOnSearch);
        System.out.println("Do something else");

        if(!executed) {
            System.out.println("Use Case 3 again");
            executed = checkPoll(pollLogStorage, compressedData, logStorage);
        }


    }

    public static boolean checkPoll(PollLogStorage pollLogStorage, LogEntry[] compressedData, ClientProxy logStorage) {
        if (pollLogStorage.isIncreaseComplete()) {
            logger.info("Successfully increased storage space By {}", pollLogStorage.getIncrementAmount());
            logger.debug("Adding logs in bulk");
            logStorage.addLogsInBulk(compressedData);
            return true;
        }
        return false;
    }
}
