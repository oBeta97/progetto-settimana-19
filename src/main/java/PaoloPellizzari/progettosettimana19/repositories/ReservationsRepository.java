package PaoloPellizzari.progettosettimana19.repositories;

import PaoloPellizzari.progettosettimana19.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {
}
