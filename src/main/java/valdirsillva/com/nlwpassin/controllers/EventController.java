package valdirsillva.com.nlwpassin.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import valdirsillva.com.nlwpassin.dto.attendee.AttendeeIdDTO;
import valdirsillva.com.nlwpassin.dto.attendee.AttendeeRequestDTO;
import valdirsillva.com.nlwpassin.dto.attendee.AttendeesListResponseDTO;
import valdirsillva.com.nlwpassin.dto.event.EventIdDTO;
import valdirsillva.com.nlwpassin.dto.event.EventRequestDTO;
import valdirsillva.com.nlwpassin.dto.event.EventResponseDTO;
import valdirsillva.com.nlwpassin.services.AttendeeService;
import valdirsillva.com.nlwpassin.services.EventService;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventservice;
    private final AttendeeService attendeeService;

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDTO> getEvent(@PathVariable String id) {
        EventResponseDTO event = this.eventservice.getEventDetail(id);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventIdDTO> createEvent(@RequestBody EventRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder) {
        EventIdDTO eventIdDTO = this.eventservice.createEvent(body);

        var uri = uriComponentsBuilder.path("/events/{id}")
                .buildAndExpand(eventIdDTO.eventId())
                .toUri();

        return ResponseEntity.created(uri).body(eventIdDTO);
    }

    @PostMapping("/{eventId}/attendees")
    public ResponseEntity<AttendeeIdDTO> registerParticipant(
            @PathVariable String eventId,
            @RequestBody AttendeeRequestDTO body,
            UriComponentsBuilder uriComponentsBuilder) {

        AttendeeIdDTO attendeeIdDTO = this.eventservice.registerAttendeeOnEvent(eventId, body);

        var uri = uriComponentsBuilder
                .path("/attendees/{attendeeId}/badge")
                .buildAndExpand(attendeeIdDTO.attendeeId())
                .toUri();

        return ResponseEntity.created(uri).body(attendeeIdDTO);
    }

    @GetMapping("/attendees/{id}")
    public ResponseEntity<AttendeesListResponseDTO> getEventAttendee(@PathVariable String id) {
        AttendeesListResponseDTO attendeeListResponse = this.attendeeService.getEventsAttendee(id);
        return ResponseEntity.ok(attendeeListResponse);
    }

}
