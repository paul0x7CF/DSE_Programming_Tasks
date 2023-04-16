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

        OutputStream outputStream = socketOne.getOutputStream();
        InputStream inputStream = socketOne.getInputStream();

        //---------------------------------------------


        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        int receivedData = dataInputStream.readInt();
        logger.debug("Received Data Stream: " + receivedData);
        receivedData++;
        dataOutputStream.writeInt(receivedData);



        //------------------------------------------


        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
        logger.debug("Received Object Stream: " + receivedMessageObject);
        receivedMessageObject.incrementNumber();
        objectOutputStream.writeObject(receivedMessageObject);

        //------------------------------------------


        while(true) {
            try{
            receivedMessageObject = (MessageObject) objectInputStream.readObject();
            logger.trace("Received Object Stream: " + receivedMessageObject);
            receivedMessageObject.incrementNumber();
            objectOutputStream.writeObject(receivedMessageObject);
            } catch(Exception e) {
                break;
            }
        }


        socketOne.close();
        // Close the socket and its streams






    }

}
