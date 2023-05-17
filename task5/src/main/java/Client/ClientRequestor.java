package Client;

import KickStartDev.RequestMessage;

public class ClientRequestor {

    //The Class handles the requests to the Remote Object on the server
    public static void invokeObjectByFireAndForget(RequestMessage requestMessage) throws Exception {
        ClientRequestHandler.sendMessage(requestMessage.marshall());

    }
}
