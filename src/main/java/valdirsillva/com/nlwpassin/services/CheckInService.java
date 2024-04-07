package valdirsillva.com.nlwpassin.services;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import valdirsillva.com.nlwpassin.domain.attendee.Attendee;
import valdirsillva.com.nlwpassin.domain.checkin.CheckIn;
import valdirsillva.com.nlwpassin.domain.checkin.exceptions.CheckInAlreadyExistsException;
import valdirsillva.com.nlwpassin.repositories.CheckinRepository;

@Service
@RequiredArgsConstructor
public class CheckInService {
    private final CheckinRepository checkinRepository;

    public void registerCheckIn(Attendee attendee) {
        this.verifyCheckInExists(attendee.getId());

        CheckIn newCheckin = new CheckIn();
        newCheckin.setAttendee(attendee);
        newCheckin.setCreatedAt(LocalDateTime.now());

        this.checkinRepository.save(newCheckin);
    }

    private void verifyCheckInExists(String attendeeId) {
        Optional<CheckIn> isCheckedIn = this.getCheckIn(attendeeId);

        if (isCheckedIn.isPresent())
            throw new CheckInAlreadyExistsException("Attendee already checked in");

    }

    public Optional<CheckIn> getCheckIn(String attendeeId) {
        return this.checkinRepository.findByAttendeeId(attendeeId);
    }
}
