package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;


@SpringBootApplication
public class Main {

    public static void main(String[] args) {


        MeetingScheduler meetingScheduler = new MeetingScheduler();
        Meeting meeting1 = new Meeting(LocalDate.now(), LocalTime.now().plusHours(1));
        Meeting meeting2 = new Meeting(LocalDate.now().plusDays(1), LocalTime.now().plusHours(1));
        Meeting meeting3 = new Meeting(LocalDate.now().plusDays(2), LocalTime.now().plusHours(1));
        Meeting meeting4 = new Meeting(LocalDate.now().plusDays(3), LocalTime.now().plusHours(1));

        meetingScheduler.addMeeting(meeting1);
        meetingScheduler.addMeeting(meeting2);
        meetingScheduler.addMeeting(meeting3);
        meetingScheduler.addMeeting(meeting4);

        for (Meeting meeting : meetingScheduler.getMeetings()) {
            System.out.println(meeting);
        }

        RestHandler restHandler = new RestHandler();
        SpringApplication.run(Main.class, args);


    }
}