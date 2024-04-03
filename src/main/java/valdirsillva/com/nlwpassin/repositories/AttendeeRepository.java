package valdirsillva.com.nlwpassin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import valdirsillva.com.nlwpassin.domain.attendee.Attendee;

public interface AttendeeRepository extends JpaRepository<Attendee, String> {

}
