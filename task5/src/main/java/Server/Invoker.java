package Server;

import Shared.IMarshall;
import Server.KickStartDev.LogEntry;
import Server.KickStartDev.RemoteObject;
import Shared.RequestMessage;
import Shared.EResult;
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
                }
                case increaseStorageSpace -> {
                    logger.info("invoking increaseStorageSpace method");
                    if(requestMessage.getResultAs().equals(EResult.CALLBACK)) {
                        logger.debug("invoking callback");
                        CallbackProxy callbackProxy = new CallbackProxy();
                        callbackProxy.callback(true);
                    }
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
