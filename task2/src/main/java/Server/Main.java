package Server;
import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        logger.info("Server Started");
        /*
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


        for( int i = 0; i < 6000; i++) {
            receivedMessageObject = (MessageObject) objectInputStream.readObject();
            logger.trace("Received Object Stream: " + receivedMessageObject);
            receivedMessageObject.incrementNumber();
            objectOutputStream.writeObject(receivedMessageObject);
        }

        objectInputStream.close();
        objectOutputStream.close();
        dataInputStream.close();
        dataOutputStream.close();
        inputStream.close();
        outputStream.close();
        socketOne.close();

        //------------------------------------------

        ExecutorService executor = Executors.newFixedThreadPool(4);
        for( int i = 0; i < 1000; i++) {
                Socket socketThreading = serverSocket.accept();
                Runnable worker = new ClientHandler(socketThreading);
                executor.execute(worker);
        }
        executor.shutdown();

        //------------------------------------------


        */
        logger.info("task4");

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket(7000);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                int receivedData = ByteBuffer.wrap(request.getData()).getInt();
                logger.debug("Received Data : " + receivedData);
                receivedData++;
                byte[] replyBuffer = ByteBuffer.allocate(Integer.BYTES).putInt(receivedData).array();
                DatagramPacket reply = new DatagramPacket(
                        replyBuffer,
                        replyBuffer.length,
                        request.getAddress(),
                        request.getPort());
                aSocket.send(reply);
            }
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();

        }









    }

}
