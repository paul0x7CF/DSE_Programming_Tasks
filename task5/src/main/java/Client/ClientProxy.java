package Client;

import KickStartDev.IRemoteObject;
import KickStartDev.LogEntry;

public class ClientProxy implements IRemoteObject {
    @Override
    public void singleLog(LogEntry entry) {

    }

    @Override
    public void removeOldLogs(int amountToRemove) {

    }

    @Override
    public void increaseStorageSpace(int increaseBy) {

    }

    @Override
    public void addLogsInBulk(LogEntry[] logBulk) {

    }

    @Override
    public LogEntry[] searchLogs(String searchTerm) {
        return new LogEntry[0];
    }
}
