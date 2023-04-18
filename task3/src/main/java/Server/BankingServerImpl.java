package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankingServerImpl extends UnicastRemoteObject implements BankingServer {
    private double[] accounts = new double[10];

    public BankingServerImpl() throws RemoteException {
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = 10000;
        }
    }

    @Override
    public void transfer(int from, int to, int amount) throws RemoteException {
        if (accounts[from] < amount) {
            throw new RemoteException("Insufficient funds");
        }
        accounts[from] -= amount;
        accounts[to] += amount;
        System.out.println("Transfered " + amount + " from " + from + " to " + to);
    }
}
