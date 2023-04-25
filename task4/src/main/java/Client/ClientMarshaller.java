package Client;

import Client.Exceptions.InvalidMethodeException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ClientMarshaller {


    // Define delimiter and escape characters as constants
    private static final String DELIMITER = ";";
    private static final String ESCAPE_CHARACTER = "\\";
    // Define escaped delimiter and escape characters as constants
    private static final String ESCAPED_DELIMITER = "\\" + DELIMITER;
    private static final String ESCAPED_ESCAPE_CHARACTER = ESCAPE_CHARACTER + ESCAPE_CHARACTER;

    // Convert the method name and arguments to a byte array message
    public static byte[] marshall(String methodName, String methodeParam) {
        StringBuilder stringBuilder = new StringBuilder();
        // Append the method name to the message
        stringBuilder.append(methodName);
        // Append the delimiter to the message
        stringBuilder.append(DELIMITER);
        // Loop through the arguments and append each to the message
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
        String methodeResult = parts[1];
        if (methodName.equals("Error")) {
            throw new InvalidMethodeException("Methode not found on Server");
        } else {
            return methodeResult;
        }
    }

    // Escape special characters in a string
    private static String escape(String s) {
        // Replace escape character with escaped escape character
        s = s.replace(ESCAPE_CHARACTER, ESCAPED_ESCAPE_CHARACTER);
        // Replace delimiter with escaped delimiter
        s = s.replace(DELIMITER, ESCAPED_DELIMITER);
        // Return the escaped string
        return s;
    }

    // Unescape special characters in a string
    private static String unescape(String s) {
        // Replace escaped escape character with escape character
        s = s.replace(ESCAPED_ESCAPE_CHARACTER, ESCAPE_CHARACTER);
        // Replace escaped delimiter with delimiter
        s = s.replace(ESCAPED_DELIMITER, DELIMITER);
        // Return the unescaped string
        return s;
    }
}
