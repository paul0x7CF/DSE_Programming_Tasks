import java.util.concurrent.BlockingQueue;

public class Copy implements Runnable{

    private BlockingQueue<Integer> inputQueue;
    private BlockingQueue<Integer> outputQueue;
    private BlockingQueue<Integer> OutputQueueX;
    private BlockingQueue<Integer> OutputQueueY;
    private BlockingQueue<Integer> OutputQueueZ;

    private boolean isFirst = true;

    public Copy(BlockingQueue<Integer> mergedQueue,
                BlockingQueue<Integer> copyOutputQueueX, BlockingQueue<Integer> copyOutputQueueY, BlockingQueue<Integer> copyOutputQueueZ,
                BlockingQueue<Integer> outputQueue) {
        this.inputQueue = mergedQueue;
        this.OutputQueueX = copyOutputQueueX;
        this.OutputQueueY = copyOutputQueueY;
        this.OutputQueueZ = copyOutputQueueZ;
        this.outputQueue = outputQueue;

    }


    @Override
    public void run(){
        while(true)
        {

            try {
                int headEl;
                if(this.isFirst){
                    final int START = 1;
                    headEl = START;
                    this.isFirst = false;
                }
                else {
                    headEl = this.inputQueue.take();
                }
                this.OutputQueueX.put(headEl);
                this.OutputQueueY.put(headEl);
                this.OutputQueueZ.put(headEl);
                this.outputQueue.put(headEl);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
