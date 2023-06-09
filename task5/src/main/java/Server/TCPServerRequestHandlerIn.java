package Server;

import Shared.IMarshall;
import Shared.RequestMessage;
import Shared.EResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerRequestHandlerIn implements Runnable {

    private static final Logger logger = LogManager.getLogger(TCPServerRequestHandlerIn.class);

    private final Invoker invoker;
    ServerSocket serverSocket;

    public TCPServerRequestHandlerIn(Invoker invoker) {
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
            logger.trace("ACK on Transport");


            byte[] response = invoker.invoke(request);

            // this if statement is to check
            // if the Client is waiting for a response after invoking
            // Called ack on target
            RequestMessage requestMessage = IMarshall.unmarshall(request);
            if (requestMessage.getResultAs().equals(EResult.ACK_ON_TARGET)) {
                logger.trace("ACK on target, Sending response with {} bytes to client", response.length);
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                logger.trace("Sending response to client");
                // Write the length of the data to the stream
                dataOutputStream.writeInt(response.length);
                // Write the data to the stream
                dataOutputStream.write(response);
                dataOutputStream.close();
            }
            logger.info("invocation finished");

            dataInputStream.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (true) {
            startTCPHandler();
        }

    }
}
