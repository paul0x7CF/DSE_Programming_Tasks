import java.util.concurrent.BlockingQueue;

public class Multby implements Runnable{

    private BlockingQueue<Integer> input;
    private BlockingQueue<Integer> output;
    private int multiByFactor;

    Multby(BlockingQueue input, BlockingQueue output, int multiByFactor){
        this.input = input;
        this.multiByFactor = multiByFactor;
        this.output = output;

    }

    @Override
    public void run(){
        while(true) {
            try {
                int headEl = this.input.take();
                int result = headEl * this.multiByFactor;

                output.put(result);


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
