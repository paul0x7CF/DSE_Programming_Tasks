package Server.KickStartDev;

import Shared.LogEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class RemoteObject {

    private static final Logger logger = LogManager.getLogger(RemoteObject.class);

    private LogEntry[] storage = new LogEntry[0];
    private int nextEntryPointer = 0;
    private final int MAX_LOG_ENTRIES = 1000;

    public synchronized void logSingleEntry(LogEntry entry) {
        assert (!Objects.isNull(entry));
        try {
            increaseLogStorage(1);
            storage[nextEntryPointer++] = entry;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized void removeOldLogs(int amountToRemove) throws InterruptedException {
        //Thread.sleep(10000);
        assert (amountToRemove > 0);

        amountToRemove = Math.min(amountToRemove, nextEntryPointer);

        int preserveAtIndex = 0;
        for (int i = amountToRemove; i < nextEntryPointer; i++) {
            storage[preserveAtIndex++] = storage[i];
        }

        nextEntryPointer -= amountToRemove;
        for (int i = nextEntryPointer; i < storage.length; i++) {
            storage[i] = null;
        }
    }

    public void addLogsInBulk(LogEntry[] logBulk) {
        assert (!Objects.isNull(logBulk));

        try {
            //increaseLogStorage(logBulk.length);
            for (int i = 0; i < logBulk.length; i++) {
                logSingleEntry(logBulk[i]);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public LogEntry[] search(String searchTerm) {
        var matchingEntries = new ArrayList<>();

        for (int i = 0; i < nextEntryPointer; i++) {
            var logEntry = storage[i];
            if (logEntry.matchesSearchTerm(searchTerm)) {
                matchingEntries.add(logEntry);
            }
        }

        return matchingEntries.toArray(new LogEntry[matchingEntries.size()]);
    }

    public int increaseLogStorage(int increaseBy) throws Exception {
        assert (increaseBy > 0);

        int oldStorageSpace = storage.length;
        int newStorageSpace = nextEntryPointer + increaseBy;
        logger.trace("needed Storage Space for: {} , current Storage is: {}, thereof occupied: {}", increaseBy, oldStorageSpace, nextEntryPointer);

        if (newStorageSpace > MAX_LOG_ENTRIES) {
            throw new Exception("Storage space exceeded");
        }

        if (oldStorageSpace >= newStorageSpace) {
            return 0;
        }

        var newStorage = new LogEntry[newStorageSpace];

        for (int i = 0; i < storage.length; i++) {
            newStorage[i] = storage[i];
        }

        this.storage = newStorage;
        return newStorageSpace - oldStorageSpace;
    }

    @Override
    public String toString() {
        return "Stored=" + Arrays.toString(storage) + ", nextEntryPointer=" + nextEntryPointer;
    }

    public int getStorageSize() {
        return storage.length;
    }
}