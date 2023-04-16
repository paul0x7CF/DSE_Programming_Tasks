package Message;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class MessageObject implements Serializable {

    private String name;

    private int number;

    byte[] dataBlob = new byte[0];

    private Date date;

    public MessageObject(String name, int number) {
        this.name = name;
        this.number = number;
        this.date = new Date(System.currentTimeMillis());
    }

    public MessageObject(String name, int number, int NUM_KILO_BYTES) {
        this.name = name;
        this.number = number;
        this.date = new Date(System.currentTimeMillis());
        this.dataBlob = fillDataBlob(NUM_KILO_BYTES);
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", dataBlob=" + dataBlob.length/1024 +
                ", date=" + date +
                '}';
    }

    public void incrementNumber() {
        this.number++;
    }

    private byte[] fillDataBlob(final int NUM_KILO_BYTES) {

        final int  NUM_BYTES = NUM_KILO_BYTES * 1024; // Calculate number of bytes needed
        byte[] byteArray = new byte[NUM_BYTES]; // Create byte array with the calculated number of bytes
        Arrays.fill(byteArray, (byte) 0); // Fill the array with 0's using Arrays.fill()
        return byteArray;
    }

    public int getNumber() {
        return number;
    }
}
