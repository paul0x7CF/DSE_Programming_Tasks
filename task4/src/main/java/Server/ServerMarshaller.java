package Server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ServerMarshaller {

    // Define delimiter and escape characters as constants
    private static final String DELIMITER = ";";

    // Convert the method name and arguments to a byte array message
    public static byte[] marshall(String methodName, String methodeResult)  {
        // Use StringBuilder to build the message
        StringBuilder sb = new StringBuilder();
        // Append the method name to the message
        sb.append(methodName);
        // Append the delimiter to the message
        sb.append(DELIMITER);
        // Loop through the arguments and append each to the message
        sb.append(methodeResult);
        // Convert the message to a string
        String message = sb.toString();
        // Convert the string to a byte array using UTF-8 encoding
        return message.getBytes(StandardCharsets.UTF_8);
    }

    // Convert a byte array message to the method name and arguments
    public static Object[] unmarshall(byte[] message) {
        // Convert the byte array to a string using UTF-8 encoding
        String str = new String(message, StandardCharsets.UTF_8);
        // Split the string into parts using the delimiter
        String[] parts = str.split(DELIMITER);

        return new Object[]{parts[0], parts[1]};
    }

}
