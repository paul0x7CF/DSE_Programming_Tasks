package Client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientRequestHandler {

    private static final Logger logger = LogManager.getLogger(ClientRequestHandler.class);

    public static byte[] sendMessage(byte[] data) {
        try {
            Socket socket = new Socket("localhost", 8080);
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            //Why is length needed?
            dataOutputStream.writeInt(data.length);
            dataOutputStream.write(data);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            byte[] response = new byte[dataInputStream.readInt()];
            //Why?
            dataInputStream.readFully(response, 0, response.length);

            //Close the connection
            dataOutputStream.close();
            dataInputStream.close();
            socket.close();

            return response;


        } catch (IOException e) {
            logger.error("Error while creating socket {}", e.getMessage());
        }
        //TODO: exception handling
        return null;
    }
}
