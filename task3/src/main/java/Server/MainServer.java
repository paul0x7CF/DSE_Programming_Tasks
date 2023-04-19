package Server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MainServer {

    private static final Logger logger = LogManager.getLogger(MainServer.class);
    public static void main(String[] args) {

        try{
            LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            Naming.rebind("rmi://localhost:" + Registry.REGISTRY_PORT + "/MyRMI", new BankingServerImpl());
        }catch(RemoteException| MalformedURLException e){
            e.printStackTrace();
        }

    }
}
