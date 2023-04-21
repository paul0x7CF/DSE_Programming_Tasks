import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> multiByOutputQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> mergedQueue = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> copyOutputQueueX = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> copyOutputQueueY = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> copyOutputQueueZ = new LinkedBlockingQueue<>();
        BlockingQueue<Integer> printInputQueue = new LinkedBlockingQueue<>();

        final int multiByFactor2 = 2;
        final int multiByFactor3 = 3;
        final int multiByFactor5 = 5;

        Copy copy = new Copy(mergedQueue, copyOutputQueueX, copyOutputQueueY, copyOutputQueueZ, printInputQueue);
        Multby multby2 = new Multby(copyOutputQueueX, multiByOutputQueue, multiByFactor2);
        Multby multby3 = new Multby(copyOutputQueueY, multiByOutputQueue, multiByFactor3);
        Multby multby5 = new Multby(copyOutputQueueZ, multiByOutputQueue, multiByFactor5);
        Merge merge = new Merge(multiByOutputQueue, mergedQueue);
        Print print = new Print(printInputQueue);


        new Thread(copy).start();
        new Thread(multby2).start();
        new Thread(multby3).start();
        new Thread(multby5).start();
        new Thread(merge).start();
        new Thread(print).start();


    }
}