package Message;

import java.util.Date;

public class MessageObject {

    private String name;

    private int number;

    private Date date;

    public MessageObject(String name, int number) {
        this.name = name;
        this.number = number;
        this.date = new Date(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return "MessageObject{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", date=" + date +
                '}';
    }

    public void incrementNumber() {
        this.number++;
    }

    public int getNumber() {
        return number;
    }
}
