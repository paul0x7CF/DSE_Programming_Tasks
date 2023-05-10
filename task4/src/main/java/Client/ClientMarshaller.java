package Client;

import Client.Exceptions.InvalidMethodeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ClientMarshaller {

    private static final Logger logger = LogManager.getLogger(ClientMarshaller.class);


    // Define delimiter and escape characters as constants
    private static final String DELIMITER = ";";

    // Convert the method name and arguments to a byte array message
    public static byte[] marshall(String methodName, String methodeParam) {
        logger.debug("marshalling methode {} with parameter {}", methodName, methodeParam);

        StringBuilder stringBuilder = new StringBuilder();
        // Append the method name to the message
        stringBuilder.append(methodName);
        // Append the delimiter to the message
        stringBuilder.append(DELIMITER);

        stringBuilder.append(methodeParam);
        // Convert the message to a string
        String message = stringBuilder.toString();
        // Convert the string to a byte array using UTF-8 encoding
        return message.getBytes(StandardCharsets.UTF_8);
    }

    // Convert a byte array message to the method name and arguments
    public static String unmarshall(byte[] message) throws InvalidMethodeException {
        // Convert the byte array to a string using UTF-8 encoding
        String str = new String(message, StandardCharsets.UTF_8);
        // Split the string into parts using the delimiter
        String[] parts = str.split(DELIMITER);
        // Extract the method name from the first part
        String methodName = parts[0];
        if (methodName.equals("Error")) {
            throw new InvalidMethodeException("Methode not found on Server");
        } else {
            String methodeResult = parts[1];
            logger.debug("unmarshalled Server response, methodeName: {}, methodeResult: {} ", methodName, methodeResult);
            return methodeResult;
        }
    }
}
