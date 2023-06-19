package testCLNT;

import main.Meeting;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

public class MeetingSchedulerTest {

    private final String api;
    private final String meetingsEndpoint;

    public MeetingSchedulerTest(String address, int port){
        this.api = address + ":" + port + "/api";
        this.meetingsEndpoint = "/meetings";

    }

    public void getMeetings() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                api + meetingsEndpoint,
                HttpMethod.GET,
                null,
                String.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }
    }

    public void addMeeting(String date, String startTime){
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> response = restTemplate.exchange(
                api + meetingsEndpoint + "?date=" + date + "&startTime=" + startTime,
                HttpMethod.POST,
                null,
                Void.class
        );

        if (response.getStatusCode().is2xxSuccessful()) {
            System.out.println("Response Body: " + response.getBody());
        } else {
            System.out.println("Error: " + response.getStatusCode());
        }

    }

    public void removeMeeting(int meetingId) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> createResponse = restTemplate.exchange(
                api + meetingsEndpoint + "/" + meetingId,
                HttpMethod.DELETE,
                null,
                Void.class
        );

        if (createResponse.getStatusCode().is2xxSuccessful()) {
            System.out.println("Successfully removed Meeting");

        } else {
            System.out.println("Error while removing Meeting: " + createResponse.getStatusCode());
        }
    }

    public void addParticipant(int meetingID, String participant) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Void> createResponse = restTemplate.exchange(
                api + meetingsEndpoint + "/" + meetingID + "?participant=" + participant,
                HttpMethod.POST,
                null,
                Void.class
        );

        if (createResponse.getStatusCode().is2xxSuccessful()) {
            System.out.println("Successfully added Participant");

        } else {
            System.out.println("Error while adding Participant: " + createResponse.getStatusCode());
        }
    }


}
