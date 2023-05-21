package Client;

import KickStartDev.EKnownMethods;
import KickStartDev.IRemoteObject;
import KickStartDev.LogEntry;
import KickStartDev.RequestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientProxy implements IRemoteObject {
    private static final Logger logger = LogManager.getLogger(ClientProxy.class);
    @Override
    public void singleLog(String entry) throws Exception {
        logger.debug("asking for singleLog methode with parameter {}", entry);
        ClientRequestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.singleLog, entry));

    }
    @Override
    public void removeOldLogs(int amountToRemove) throws Exception {
        logger.debug("asking for removeOldLogs methode with parameter {}", amountToRemove);
        ClientRequestor.handleSyncWithServer(new RequestMessage(EKnownMethods.removeOldLogs, amountToRemove));

    }

    @Override
    public void increaseStorageSpace(int increaseBy) {

    }

    @Override
    public void addLogsInBulk(String[] logBulk) {
        logger.debug("asking for addLogsInBulk methode with {} parameters", logBulk.length);
        //Compromise String Array to send with the GZIP java algorithm
        //ClientRequestor.invokeObjectByFireAndForget(new RequestMessage(EKnownMethods.addLogsInBulk, logBulk));

    }

    @Override
    public LogEntry[] searchLogs(String searchTerm) {
        return new LogEntry[0];
    }
}
