package main;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


@RestController
@RequestMapping("/api")
public class RestHandler {

    private final MeetingScheduler meetingScheduler = new MeetingScheduler();

    // Bean for Class in Constructor

/*    public RestHandler(MeetingScheduler meetingScheduler) {
        this.meetingScheduler = meetingScheduler;
    }*/

    public RestHandler() {
        Meeting meeting1 = new Meeting(LocalDate.now(), LocalTime.now().plusHours(1));
        Meeting meeting2 = new Meeting(LocalDate.now().plusDays(1), LocalTime.now().plusHours(1));
        Meeting meeting3 = new Meeting(LocalDate.now().plusDays(2), LocalTime.now().plusHours(1));
        Meeting meeting4 = new Meeting(LocalDate.now().plusDays(3), LocalTime.now().plusHours(1));

        meetingScheduler.addMeeting(meeting1);
        meetingScheduler.addMeeting(meeting2);
        meetingScheduler.addMeeting(meeting3);
        meetingScheduler.addMeeting(meeting4);
    }



    @RequestMapping(value = "/meetings", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<ArrayList<Meeting>> getMeetings() {
        if (meetingScheduler.getMeetings().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(meetingScheduler.getMeetings());
    }

    @RequestMapping(value = "/meetings", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Meeting> addMeeting(@RequestParam @DateTimeFormat(pattern = "dd.MM.yy") LocalDate date,
                                              @RequestParam @DateTimeFormat(pattern = "HH:mm") LocalTime startTime) {
        Meeting newMeeting = new Meeting(date, startTime);
        meetingScheduler.addMeeting(newMeeting);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/meetings/{meetingId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> removeMeeting(@PathVariable("meetingId") String meetingId) {
        UUID uuid = UUID.fromString(meetingId);
        meetingScheduler.removeMeeting(uuid);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/meetings/{meetingId}", method = RequestMethod.POST)
    public ResponseEntity<Void> addParticipant(@PathVariable("meetingId") String meetingId, @RequestParam String participant) {
        UUID uuid = UUID.fromString(meetingId);
        meetingScheduler.addParticipant(uuid, participant);
        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<HashMap<UUID,Meeting>> test() throws JsonProcessingException {
        HashMap<UUID, Meeting> test = new HashMap<>();
        test.put(UUID.randomUUID(), new Meeting(LocalDate.now(), LocalTime.now()));

        return ResponseEntity.ok(test);

        /*ArrayList<String> test = new ArrayList<>();
        test.add("Test");
        test.add("Test2");
        return ResponseEntity.ok(test);
        //return ResponseEntity.ok("Test");*/
    }


}
