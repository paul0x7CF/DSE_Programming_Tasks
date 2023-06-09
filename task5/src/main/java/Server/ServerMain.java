package Server;

import Server.KickStartDev.RemoteObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;


public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);

    public static void main(String[] args) throws InterruptedException {
        RemoteObject myRemoteObject = new RemoteObject();
        System.out.println(myRemoteObject);

        Invoker myInvoker = new Invoker(myRemoteObject);
        TCPServerRequestHandlerIn myTCPServerRequestHandler = new TCPServerRequestHandlerIn(myInvoker);
        UDPServerRequestHandlerIn myServerRequestHandler = new UDPServerRequestHandlerIn(myInvoker);

        new Thread(myTCPServerRequestHandler,"TCP-Input").start();
        new Thread(myServerRequestHandler,"UDP-Input").start();

        int currentStorageSpace = myRemoteObject.getStorageSize();
        while (true) {
            sleep(1000);
            System.out.println(myRemoteObject);
            /*
            if (myRemoteObject.getStorageSize() != currentStorageSpace) {
                currentStorageSpace = myRemoteObject.getStorageSize();
                System.out.println(myRemoteObject);
            }
            */
        }

    }
}
