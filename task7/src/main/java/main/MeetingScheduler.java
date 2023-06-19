package main;

import org.springframework.context.annotation.Bean;

import java.util.*;


public class MeetingScheduler {

    private Map<Integer, Meeting> meetings;

    public MeetingScheduler() {
        this.meetings = new HashMap<>();
    }

    public void addMeeting(Meeting meeting) {
        this.meetings.put(this.meetings.size(), meeting);
    }

    public void removeMeeting(Integer meetingId) {
        this.meetings.remove(meetingId);
    }

    public Map<Integer, Meeting> getMeetings() {
        return meetings;
    }

    public void addParticipant(int id, String participant) {
        this.meetings.get(id).addParticipant(participant);
    }

    public Meeting getMeetingById(int id) {
        return this.meetings.get(id);
    }
}
