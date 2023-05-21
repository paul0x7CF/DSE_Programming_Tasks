package Server;

import KickStartDev.RemoteObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.Thread.sleep;


public class ServerMain {

    private static final Logger logger = LogManager.getLogger(ServerMain.class);

    public static void main(String[] args) throws InterruptedException {
        RemoteObject myRemoteObject = new RemoteObject();
        System.out.println(myRemoteObject);

        Invoker myInvoker = new Invoker(myRemoteObject);
        TCPServerRequestHandler myTCPServerRequestHandler = new TCPServerRequestHandler(myInvoker);
        UDPServerRequestHandler myServerRequestHandler = new UDPServerRequestHandler(myInvoker);

        new Thread(myTCPServerRequestHandler).start();
        new Thread(myServerRequestHandler).start();

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
