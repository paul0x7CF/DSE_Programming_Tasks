package Client;

import Server.IBankingServer;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;


public class MainClient {

    private static final Logger logger = LogManager.getLogger(MainClient.class);
    public static void main(String[] args) {

        logger.info("Starting client...");

        try{
            IBankingServer stub = (IBankingServer) Naming.lookup("//localhost:" + Registry.REGISTRY_PORT + "/MyRMI");

            System.out.println("Total Bank Balance: " + stub.audit());
            logger.debug("Balance of Account Nr.[0]: {}", stub.getBalance(0));
            logger.debug("Balance of Account Nr.[1]: {}", stub.getBalance(1));
            logger.info("Transfering 1000 from Account Nr.[0] to Account Nr.[1]");
            stub.transfer(0, 1, 1000);
            logger.debug("Balance of Account Nr.[1]: {}", stub.getBalance(0));
            logger.debug("Balance of Account Nr.[1]: {}", stub.getBalance(1));
            System.out.println("Total Bank Balance:" + stub.audit());

        }catch (MalformedURLException| NotBoundException| RemoteException e){
            e.printStackTrace();
        }

    }
}