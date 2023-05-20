package Server;

import KickStartDev.IMarshall;
import KickStartDev.LogEntry;
import KickStartDev.RemoteObject;
import KickStartDev.RequestMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Invoker {

    private static final Logger logger = LogManager.getLogger(Invoker.class);
    private RemoteObject remoteObject;

     public Invoker(RemoteObject remoteObject) {
         this.remoteObject = remoteObject;
     }

    public byte[] invoke(byte[] data) {
        try {
            RequestMessage requestMessage = IMarshall.unmarshall(data);

            switch (requestMessage.getMethod()) {
                case singleLog -> {
                    logger.debug("invoking singleLog method");
                    remoteObject.logSingleEntry(new LogEntry((String) requestMessage.getRequestData()));
                }
                case removeOldLogs -> {
                    logger.debug("invoking removeOldLogs method");
                    remoteObject.removeOldLogs((int) requestMessage.getRequestData());
                }
                case increaseStorageSpace -> {
                    logger.debug("invoking increaseStorageSpace method");
                    //TODO: Implement this case
                }
                case addLogsInBulk -> {
                    logger.debug("invoking addLogsInBulk method");
                    //TODO: Implement this case
                }
                case searchLogs -> {
                    logger.debug("invoking searchLogs method");
                    //TODO: Implement this case
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
