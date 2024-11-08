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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "event_dt", nullable = false)
    private LocalDateTime eventDt;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String place;
    @Column(name = "available_seats")
    private int availableSeats;


    @OneToMany
    @JoinColumn(name = "organizer_id")
    private User organizerId;

    public Event(LocalDateTime eventDt, String title, String description, String place, User organizerId, int availableSeats) {
        this.eventDt = eventDt;
        this.title = title;
        this.description = description;
        this.place = place;
        this.organizerId = organizerId;
        this.availableSeats = availableSeats;
    }
}
