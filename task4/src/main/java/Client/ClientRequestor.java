package Client;

import Client.Exceptions.InvalidMethodeException;

public class ClientRequestor {

    public static String request(String methodeName, String methodeParam) throws InvalidMethodeException {
        byte[] request = ClientMarshaller.marshall(methodeName, methodeParam);
        byte[] response = ClientRequestHandler.sendMessage(request);
        String result = ClientMarshaller.unmarshall(response);
        return result;


    }
}
