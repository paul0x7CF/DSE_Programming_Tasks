package Client;

import Shared.LogEntry;

public class CompressData {

    public static LogEntry[] compress(LogEntry[] data) {
        try {
            Thread.sleep(10);
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
