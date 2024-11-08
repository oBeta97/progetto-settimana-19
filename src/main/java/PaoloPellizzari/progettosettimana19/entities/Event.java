package PaoloPellizzari.progettosettimana19.entities;


import PaoloPellizzari.progettosettimana19.payloads.EventDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
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
    private LocalDate eventDt;
    @Column(nullable = false)
    private String title;
    private String description;
    @Column(nullable = false)
    private String place;
    @Column(name = "available_seats")
    private int availableSeats;


    @ManyToOne
    @JoinColumn(name = "organizer_id")
    private User organizerId;

    public Event(LocalDate eventDt, String title, String description, String place, User organizerId, int availableSeats) {
        this.eventDt = eventDt;
        this.title = title;
        this.description = description;
        this.place = place;
        this.organizerId = organizerId;
        this.availableSeats = availableSeats;
    }

    public Event(EventDTO eventDTO, User organizerId) {
        this.eventDt = eventDTO.eventDt();
        this.title = eventDTO.title();
        this.description = eventDTO.description();
        this.place = eventDTO.place();
        this.organizerId = organizerId;
        this.availableSeats = eventDTO.availableSeats();
    }


}
