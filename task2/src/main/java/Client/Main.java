package Client;

import Message.MessageObject;

import java.net.*;
import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        logger.info("Client Started");
        logger.debug("hello debug");
        logger.error("hello error");
        logger.fatal("hello fatal");
        logger.trace("hello trace");
        logger.warn("hello warn");

        // Open your connection to a server, at port 1254
        Socket socketOne = null;
        while (socketOne == null) {
            try {
                socketOne = new Socket("localhost", 1254);

            } catch (IOException e) {
                System.out.println("Connection not found, retrying...");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        OutputStream outputStream = socketOne.getOutputStream();
        InputStream inputStream = socketOne.getInputStream();

        //---------------------------------------------


        DataInputStream dataInputStream = new DataInputStream(inputStream);
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);


        dataOutputStream.writeInt(10);
        int receivedData = dataInputStream.readInt();



        System.out.println("Received data: " + receivedData);


        //---------------------------------------------



        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(new MessageObject("Client_1", 10));
        MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();
        objectInputStream.close();
        objectOutputStream.close();

        System.out.println("Received message: " + receivedMessageObject);




        socketOne.close();


    }
}