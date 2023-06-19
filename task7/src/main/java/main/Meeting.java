package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Meeting {
    private LocalDate date;
    private LocalTime startTime;
    private List<String> participants;

    public Meeting(LocalDate date, LocalTime startTime) {
        this.date = date;
        this.startTime = startTime;
        this.participants = new ArrayList<>();
    }

    public void addParticipant(String participant) {
        this.participants.add(participant);
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public List<String> getParticipants() {
        return participants;
    }
}
