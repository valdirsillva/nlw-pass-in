package valdirsillva.com.nlwpassin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valdirsillva.com.nlwpassin.domain.attendee.Attendee;
import valdirsillva.com.nlwpassin.domain.checkin.Checkin;
import valdirsillva.com.nlwpassin.dto.attendee.AttendeeDetails;
import valdirsillva.com.nlwpassin.dto.attendee.AttendeesListResponseDTO;
import valdirsillva.com.nlwpassin.repositories.AttendeeRepository;
import valdirsillva.com.nlwpassin.repositories.CheckinRepository;

@Service
@RequiredArgsConstructor
public class AttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final CheckinRepository checkinRepository;

    public List<Attendee> getAllAttendeesFromEvent(String eventId) {
        return this.attendeeRepository.findByEventId(eventId);
    }

    public AttendeesListResponseDTO getEventsAttendee(String eventId) {
        List<Attendee> attendeeList = this.getAllAttendeesFromEvent(eventId);

        List<AttendeeDetails> attendeeDetailsList = attendeeList.stream().map(attendee -> {
            Optional<Checkin> checkIn = this.checkinRepository.findByAttendeeId(attendee.getId());
            LocalDateTime checkedInAt = checkIn.isPresent() ? checkIn.get().getCreatedAt() : null;
            return new AttendeeDetails(attendee.getId(), attendee.getName(), attendee.getEmail(),
                    attendee.getCreatedAt(), checkedInAt);
        }).toList();

        return new AttendeesListResponseDTO(attendeeDetailsList);
    }
}
