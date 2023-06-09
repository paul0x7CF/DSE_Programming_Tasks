package Client;

public class CompressData {

    public static String[] compress(String[] data) {
        try {
            Thread.sleep(10);
            return data;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
