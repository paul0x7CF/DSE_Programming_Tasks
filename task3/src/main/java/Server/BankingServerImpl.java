package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;

public class BankingServerImpl extends UnicastRemoteObject implements IBankingServer {
    private double[] accounts = new double[10];

    public BankingServerImpl() throws RemoteException {
        Arrays.fill(accounts, 10000);
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

    @Override
    public double getBalance(int account) throws RemoteException {
        return accounts[account];
    }

    @Override
    public String audit() throws RemoteException {
        double sum = 0;
        for (double account : accounts) {
            sum += account;
        }
        return "Total balance: " + sum;
    }
}
