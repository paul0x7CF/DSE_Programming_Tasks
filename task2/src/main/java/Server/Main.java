package Server;
import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.io.*;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        logger.info("Server Started");

        // Register service on port 1254
        ServerSocket serverSocket = new ServerSocket(1254);
        Socket socketOne = serverSocket.accept();
        // Wait and accept a connection
        // Get a communication stream
        // associated with the socket

        //---------------------------------------------

        OutputStream outputStreamOne = socketOne.getOutputStream();
        InputStream inputStreamOne = socketOne.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStreamOne);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStreamOne);

        int receivedData = dataInputStream.readInt();
        logger.debug("Received Data Stream: " + receivedData);
        receivedData++;
        dataOutputStream.writeInt(receivedData);



        //------------------------------------------


        InputStream inputStreamTwo = socketOne.getInputStream();
        OutputStream outputStreamTwo = socketOne.getOutputStream();

        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStreamTwo);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStreamTwo);

        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
        logger.debug("Received Object Stream: " + receivedMessageObject);
        receivedMessageObject.incrementNumber();
        objectOutputStream.writeObject(receivedMessageObject);

        //------------------------------------------

        socketOne.close();
        // Close the socket and its streams






    }

}
