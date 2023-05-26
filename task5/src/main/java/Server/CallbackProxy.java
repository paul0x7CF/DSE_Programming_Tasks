package Server;

import Client.CallbackIncLogStorage;
import Shared.EKnownMethods;
import Shared.ResponseMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CallbackProxy implements CallbackIncLogStorage {

    private static final Logger logger = LogManager.getLogger(CallbackProxy.class);

    @Override
    public void callback(boolean isSuccessful) {
        ResponseMessage responseMessage = new ResponseMessage(EKnownMethods.Callback, isSuccessful);
        try {
            ServerRequestHandlerOut.sendViaUDP(responseMessage.marshall());
        } catch (Exception e) {
            logger.error("Error while marshalling the message");
            throw new RuntimeException(e);
        }
    }

}
