import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;

public class Merge implements Runnable {

    private BlockingQueue<Integer> inputFromMultiBy;
    private BlockingQueue<Integer> mergedOutput;

    public Merge(BlockingQueue inputFromMultiBy, BlockingQueue mergedOutput) {
        this.inputFromMultiBy = inputFromMultiBy;
        this.mergedOutput = mergedOutput;
    }


    @Override
    public void run() {
        while(true) {

            LinkedList<Integer> listToMerge = new LinkedList<>();

            if (inputFromMultiBy.size() >= 3) {

                for (int i = 0; i < 3; i++){
                    try {
                        listToMerge.add(inputFromMultiBy.take());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                listToMerge.stream().distinct();

                mergedOutput.addAll(listToMerge);

            }
        }

    }

}
