package PaoloPellizzari.progettosettimana19.repositories;

import PaoloPellizzari.progettosettimana19.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventsRepository extends JpaRepository<Event, Long> {
}
