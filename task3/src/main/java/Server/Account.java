package Server;

import java.util.concurrent.BlockingDeque;

public class Account implements Runnable{
    private int Id;
    private double balance;

    private BlockingDeque<Integer> withdrawQueue;
    private BlockingDeque<Integer> depositQueue;

    public Account(int id) {
        this.Id = id;
        this.balance = 10000.0;
    }

    @Override
    public void run() {
        while(true)
        {
            try {
                int withdrawAmount = withdrawQueue.take();
                int depositAmount = depositQueue.take();

                if (balance < withdrawAmount) {
                    throw new RuntimeException("Insufficient funds");
                }
                balance -= withdrawAmount;
                balance += depositAmount;
                System.out.println("Transfered " + withdrawAmount + " from " + Id + " to " + Id);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
