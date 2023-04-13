import java.util.concurrent.BlockingQueue;

public class Print implements Runnable{

    private BlockingQueue<Integer> queueToPrint;

    public Print(BlockingQueue inputFromCopy){
        this.queueToPrint = inputFromCopy;
    }

    @Override
    public void run(){
        while(true){
            int output = 0;
            try {
                output = queueToPrint.take();
                if(output > 100) System.exit(0);
                System.out.print(output + ", ");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
