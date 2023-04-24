package Client;

import Message.MessageObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {


        logger.info("Client Started");

        /*
        // Open your connection to a server, at port 1254
        Socket socketOne = null;
        while (socketOne == null) {
            try {
                socketOne = new Socket("localhost", 1254);

            } catch (IOException e) {
                logger.trace("Connection not found, retrying...");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        logger.info("Connection established");
        // if Connection failure, Client will not know
        // only when it tries to send data an exception will occur


        buildDataStreamConnection(socketOne);

        buildObjectStreamConnection(socketOne);
        socketOne.close();

        Socket socketTwo = new Socket("localhost", 1254);
        sendDataBlob(socketTwo);
        socketTwo.close();

        threadingConnection();

         */


        logger.info("task4");

        DatagramSocket aSocket = null;
        try {
            aSocket = new DatagramSocket();
            int message = 10;
            byte[] buffer = ByteBuffer.allocate(Integer.BYTES).putInt(message).array();
            InetAddress aHost = InetAddress.getByName("localhost");
            int serverPort = 7000;
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, aHost, serverPort);
            aSocket.send(request);

            byte[] receivedBuffer = new byte[1000];
            DatagramPacket receivePacket  = new DatagramPacket(receivedBuffer, receivedBuffer.length);
            aSocket.receive(receivePacket);
            int receivedMessage = ByteBuffer.wrap(receivePacket.getData()).getInt();
            System.out.println("Reply: " + receivedMessage);
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }


    }

    private static void buildDataStreamConnection(Socket socket) {

        try {
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeInt(10);
            int receivedData = dataInputStream.readInt();

            System.out.println("Received message from Data Stream: " + receivedData);

        } catch (IOException e) {
            logger.error("Error in dataStreamConnection: " + e.getMessage());
        }

    }

    private static void buildObjectStreamConnection(Socket socket) {
        try {

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            //ObjectOutputStream have to be created before ObjectInputStream
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

            objectOutputStream.writeObject(new MessageObject("Client_1", 10));
            MessageObject receivedMessageObject = (MessageObject) objectInputStream.readObject();

            System.out.println("Received message from Object Stream: " + receivedMessageObject);

            outputStream.close();
            inputStream.close();
            objectOutputStream.close();
            objectInputStream.close();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("Error in objectStreamConnection: " + e.getMessage());
        }
    }

    private static void sendDataBlob(Socket socket) {


        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());


            int[] dataBlob = {1, 2, 4, 8, 16, 128};
            ArrayList<Double> processingTime = new ArrayList<>();
            for (int eachDataBlob : dataBlob) {

                MessageObject messageObject = new MessageObject("Client_2", 0, eachDataBlob);
                System.out.println("Sending message Object 1000 times with dataBlob: " + eachDataBlob);

                for (int i = 0; i < 1000; i++) {
                    if (i % 50 == 0) System.out.print(".");
                    messageObject.setDate(new Date(System.currentTimeMillis()));

                    objectOutputStream.writeObject(messageObject);

                    messageObject = (MessageObject) objectInputStream.readObject();

                    double currentprocessingTime = ((double) (System.nanoTime() - messageObject.getDate().getTime()) / 1000000.0);
                    processingTime.add((double) currentprocessingTime);

                    logger.trace("Time difference: " + (currentprocessingTime));
                    logger.trace("Received message from Object Stream: " + messageObject);
                }
                int entries = processingTime.size();
                int sum = 0;
                for (Double eachProcessingTime : processingTime) {
                    sum += eachProcessingTime;
                }
                System.out.print("\n");
                System.out.println("Average processing time: " + sum / entries + "ms");


            }

            objectOutputStream.close();
            objectInputStream.close();
        } catch (IOException e) {
            logger.error("Error in sendDataBlob: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            logger.error("Error in sendDataBlob: " + e.getMessage());
        }

    }

    private static void threadingConnection() {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        try {
            for (int i = 0; i < 1000; ++i) {
                Socket socketThreading = null;
                socketThreading = new Socket("localhost", 1254);
                Runnable worker = new ConnectionHandler(socketThreading, "Client " + i, i);
                executor.execute(worker);
            }
            executor.shutdown();
        } catch (IOException e) {
            logger.error("Error in threadingConnection: {}", e.getMessage());
        }
    }
}