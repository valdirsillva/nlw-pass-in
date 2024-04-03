package valdirsillva.com.nlwpassin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import valdirsillva.com.nlwpassin.domain.checkin.Checkin;

public interface CheckinRepository extends JpaRepository<Checkin, Integer> {

}
