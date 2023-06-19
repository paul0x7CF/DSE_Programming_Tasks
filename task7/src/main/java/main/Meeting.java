package main;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Meeting implements Serializable {
    private final UUID meetingId;
    private LocalDate date;
    private LocalTime startTime;
    private List<String> participants;

    public Meeting(LocalDate date, LocalTime startTime) {
        this.meetingId = UUID.randomUUID();
        this.date = date;
        this.startTime = startTime;
        this.participants = new LinkedList<>();
    }
    public UUID getMeetingId() {
        return meetingId;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingId=" + meetingId +
                ", date=" + date +
                ", startTime=" + startTime +
                ", participants=" + participants +
                '}';
    }

    public void addParticipant(String participant) {
        this.participants.add(participant);
    }
}
