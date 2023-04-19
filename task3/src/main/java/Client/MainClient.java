package Client;

import Server.IBankingServer;
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
            IBankingServer stub = (IBankingServer) Naming.lookup("rmi://localhost:" + Registry.REGISTRY_PORT + "/MyRMI");
        }catch (MalformedURLException| NotBoundException| RemoteException e){
            e.printStackTrace();
        }

    }
}