package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerRequestHandler implements Runnable {

    private static final Logger logger = LogManager.getLogger(TCPServerRequestHandler.class);

    private final Invoker invoker;
    ServerSocket serverSocket;

    public TCPServerRequestHandler(Invoker invoker) {
        this.invoker = invoker;
        try {
            this.serverSocket = new ServerSocket(8080);
            logger.info("TCP Connection started on port 8080");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startTCPHandler() {
        try {
            logger.info("listening for TCP request");
            Socket socket = serverSocket.accept();

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            logger.debug("Data was received from client over TCP connection");

            byte[] request = new byte[dataInputStream.readInt()];
            dataInputStream.readFully(request, 0, request.length);

            invoker.invoke(request);

            //TODO: check if the Sync with server is ok so
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            final int ACK = 1;
            dataOutputStream.writeInt(ACK);

            logger.info("invocation finished");

            dataInputStream.close();
            dataOutputStream.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true)
        {
            startTCPHandler();
        }

    }
}
