package main;

import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class MeetingScheduler {

    private HashMap<UUID, Meeting> meetings;

    public MeetingScheduler() {
        this.meetings = new HashMap<>();
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.put(meeting.getMeetingId(), meeting);
    }

    public void removeMeeting(UUID meetingId) {
        this.meetings.remove(meetingId);
    }

    public ArrayList<Meeting> getMeetings() {
        return new ArrayList<>(this.meetings.values());
    }

    public void addParticipant(UUID uuid, String participant) {
        this.meetings.get(uuid).addParticipant(participant);
    }
}
