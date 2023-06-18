package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Meeting {
    private final UUID meetingId;
    private LocalDate date;
    private LocalTime startTime;
    private List<String> participants;

    public Meeting(LocalDate date, LocalTime startTime) {
        this.meetingId = UUID.randomUUID();
        this.date = date;
        this.startTime = startTime;
    }
}
