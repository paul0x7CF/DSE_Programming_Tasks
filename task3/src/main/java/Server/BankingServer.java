package Server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BankingServer extends Remote {
    void transfer(int from, int to, int amount) throws RemoteException;

    double getBalance(int account) throws RemoteException;

    String audit() throws RemoteException;
}
