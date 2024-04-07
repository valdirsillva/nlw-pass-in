package valdirsillva.com.nlwpassin.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import valdirsillva.com.nlwpassin.domain.checkin.CheckIn;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String id);
}
