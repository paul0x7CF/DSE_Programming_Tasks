package Server;

import Shared.*;
import Shared.LogEntry;
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
                    int removeAbout = (int) requestMessage.getRequestData();
                    logger.info("invoking removeOldLogs method about {} logs", removeAbout);
                    remoteObject.removeOldLogs(removeAbout);
                    // Return a value for the ACK on target
                    return new byte[1];
                }
                case increaseStorageSpace -> {
                    logger.info("invoking increaseStorageSpace method");
                    final int resultInc = remoteObject.increaseLogStorage((int) requestMessage.getRequestData());
                    ResponseMessage responseMessage = new ResponseMessage(EKnownMethods.increaseStorageSpace, resultInc);
                    return responseMessage.marshall();

                }
                case addLogsInBulk -> {
                    logger.debug("invoking addLogsInBulk method");
                    remoteObject.addLogsInBulk((LogEntry[]) requestMessage.getRequestData());
                }
                case searchLogs -> {
                    logger.info("invoking searchLogs method");
                    logger.debug("searching for {}", requestMessage.getRequestData());
                    LogEntry[] foundLogs = remoteObject.search((String) requestMessage.getRequestData());

                    logger.debug("found {} logs", foundLogs.length);
                    if(requestMessage.getResultAs().equals(EResult.CALLBACK)) {
                        logger.debug("invoking callback");
                        CallbackProxy callbackProxy = new CallbackProxy();
                        callbackProxy.callback(foundLogs);
                    }


                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
