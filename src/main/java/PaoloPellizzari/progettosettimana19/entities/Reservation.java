package PaoloPellizzari.progettosettimana19.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "reservation_dt", nullable = false)
    private LocalDateTime reservationDt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event eventId;

    public Reservation(LocalDateTime reservationDt, User userId, Event eventId) {
        this.reservationDt = reservationDt;
        this.userId = userId;
        this.eventId = eventId;
    }
}
