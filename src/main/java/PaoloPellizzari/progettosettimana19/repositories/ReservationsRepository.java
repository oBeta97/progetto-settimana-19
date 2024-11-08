package PaoloPellizzari.progettosettimana19.repositories;

import PaoloPellizzari.progettosettimana19.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationsRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r JOIN r.userId u JOIN r.eventId e WHERE u.id = :userId and e.eventDt = :eventDt")
    Optional<Reservation> findReservationByUserIdAndEventDt(long userId, LocalDate eventDt);
}
