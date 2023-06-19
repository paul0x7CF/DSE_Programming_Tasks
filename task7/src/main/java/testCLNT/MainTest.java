package testCLNT;

import java.time.LocalDate;
import java.time.LocalTime;

public class MainTest {
    public static void main(String[] args) {
        final String address = "http://localhost";
        final int port = 8080;

        MeetingSchedulerTest meetingSchedulerTest = new MeetingSchedulerTest(address, port);

        meetingSchedulerTest.addMeeting("12.01.23", "12:00");
        meetingSchedulerTest.addMeeting("13.01.23", "12:00");
        meetingSchedulerTest.getMeetings();
        meetingSchedulerTest.removeMeeting(1);
        meetingSchedulerTest.addParticipant(2, "John");


    }
}
