package PaoloPellizzari.progettosettimana19.repositories;

import PaoloPellizzari.progettosettimana19.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByPlaceAndEventDt(String place, LocalDate eventDt);


}
