package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class BankingServerImpl extends UnicastRemoteObject implements IBankingServer, Runnable {
    private double[] accounts = new double[10];
    private HashMap<Integer,Account> accountsMap= new LinkedHashMap<Integer, Account>();

    public BankingServerImpl() throws RemoteException {
        Arrays.fill(accounts, 10000);

        for(int i = 0; i < 10; i++){
            accountsMap.put(i, new Account(i));
        }
        for(Account eachAccount : accountsMap.values()) {
            new Thread(eachAccount);
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

    @Override
    public void run() {

    }
}
