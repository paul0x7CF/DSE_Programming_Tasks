package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerRequestHandler {

    private static final Logger logger = LogManager.getLogger(ServerRequestHandler.class);

    public void startHandler() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            logger.info("Server started on port 8080");
            while (true) {
                Socket socket = serverSocket.accept();
                logger.debug("receive request");
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());

                byte[] request = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(request, 0, request.length);

                byte[] response = Invoker.invoke(request);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                dataOutputStream.writeInt(response.length);
                dataOutputStream.write(response);

                //Close the connection
                dataOutputStream.close();
                dataInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            logger.error("Error while creating socket {}", e.getMessage());
        }

        throw new RuntimeException("Server stopped");
    }
}
