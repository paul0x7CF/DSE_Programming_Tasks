package Client;

import Shared.IMarshall;
import Shared.RequestMessage;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class PollLogStorageListener implements Runnable{

    private static final Logger logger = LogManager.getLogger(PollLogStorageListener.class);

    private PollLogStorage pollLogStorage;
    private RequestMessage requestMessage;

    public PollLogStorageListener(PollLogStorage pollLogStorage, RequestMessage requestMessage) {
        this.pollLogStorage = pollLogStorage;
        this.requestMessage = requestMessage;
    }
    @Override
    public void run() {

        try {
            byte[] response = ClientRequestHandlerOut.sendMessageViaTCPTargetAck(requestMessage.marshall());
            ResponseMessage responseMessage = IMarshall.unmarshall(response);
            int incBy = (int) responseMessage.getResponseData();
            logger.trace("Received successfully increased LogStorage on Server with amount {}", incBy);
            this.pollLogStorage.setIncrementAmount(incBy);
            this.pollLogStorage.setLogStorageComplete(true);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
