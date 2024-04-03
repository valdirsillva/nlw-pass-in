package valdirsillva.com.nlwpassin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import valdirsillva.com.nlwpassin.domain.event.Event;

public interface EventRepository extends JpaRepository<Event, String> {

}
