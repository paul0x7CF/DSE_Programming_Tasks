package Server;

import Client.ClientMain;
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
        ServerRequestHandler myServerRequestHandler = new ServerRequestHandler(myInvoker);

        new Thread(myServerRequestHandler).start();

        int currentStorageSpace = myRemoteObject.getStorageSize();
        while (true) {
            sleep(10);

            if (myRemoteObject.getStorageSize() != currentStorageSpace) {
                currentStorageSpace = myRemoteObject.getStorageSize();
                System.out.println(myRemoteObject);
            }
        }

    }
}
