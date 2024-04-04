package valdirsillva.com.nlwpassin.services;

import lombok.RequiredArgsConstructor;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;
import valdirsillva.com.nlwpassin.domain.attendee.Attendee;
import valdirsillva.com.nlwpassin.domain.event.Event;
import valdirsillva.com.nlwpassin.domain.event.exceptions.EventNotFoundException;
import valdirsillva.com.nlwpassin.dto.event.EventIdDTO;
import valdirsillva.com.nlwpassin.dto.event.EventRequestDTO;
import valdirsillva.com.nlwpassin.dto.event.EventResponseDTO;
import valdirsillva.com.nlwpassin.repositories.EventRepository;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final AttendeeService attendeeService;

    public EventResponseDTO getEventDetail(String eventId) {
        Event event = this.eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found with ID:" + eventId));

        List<Attendee> attendeeList = this.attendeeService.getAllAttendeesFromEvent(eventId);
        return new EventResponseDTO(event, attendeeList.size());
    }

    public EventIdDTO createEvent(EventRequestDTO eventDTO) {
        Event newEvent = new Event();

        newEvent.setTitle(eventDTO.title());
        newEvent.setDetails(eventDTO.details());
        newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
        newEvent.setSlug(this.createSlug(eventDTO.title()));

        this.eventRepository.save(newEvent);

        return new EventIdDTO(newEvent.getId());
    }

    private String createSlug(String text) {
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
                .replaceAll("[\\w\\s]", "")
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}
