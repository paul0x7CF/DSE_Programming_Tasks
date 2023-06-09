package Server;

import Shared.*;
import Server.KickStartDev.LogEntry;
import Server.KickStartDev.RemoteObject;
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
                    logger.info("invoking singleLog method");
                    remoteObject.logSingleEntry(new LogEntry((String) requestMessage.getRequestData()));
                }
                case removeOldLogs -> {
                    logger.info("invoking removeOldLogs method");
                    remoteObject.removeOldLogs((int) requestMessage.getRequestData());
                    return new byte[0];
                }
                case increaseStorageSpace -> {
                    logger.info("invoking increaseStorageSpace method");
                    final int resultInc = remoteObject.increaseLogStorage((int) requestMessage.getRequestData());

                    if(requestMessage.getResultAs().equals(EResult.CALLBACK)) {
                        logger.debug("invoking callback with result {}", resultInc);
                        CallbackProxy callbackProxy = new CallbackProxy();
                        callbackProxy.callback(resultInc);
                    }
                }
                case addLogsInBulk -> {
                    logger.debug("invoking addLogsInBulk method");
                    // TODO: Implement this case
                }
                case searchLogs -> {
                    logger.info("invoking searchLogs method");
                    LogEntry[] foundLogs = remoteObject.search((String) requestMessage.getRequestData());
                    String[] foundLogsAsString = new String[foundLogs.length];
                    for (int i = 0; i < foundLogs.length; i++) {
                        foundLogsAsString[i] = foundLogs[i].toString();
                    }
                    ResponseMessage responseMessage = new ResponseMessage(EKnownMethods.searchLogs, foundLogsAsString);
                    return responseMessage.marshall();


                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
