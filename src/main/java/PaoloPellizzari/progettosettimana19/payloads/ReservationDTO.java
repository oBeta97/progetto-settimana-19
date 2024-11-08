package PaoloPellizzari.progettosettimana19.payloads;

import PaoloPellizzari.progettosettimana19.entities.Event;
import PaoloPellizzari.progettosettimana19.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record ReservationDTO(

        @NotEmpty(message = "Reservation date time is mandatory")
        LocalDateTime reservationDt,
        @NotEmpty(message = "User id is mandatory")
        @Size(min = 0, message = "User id number needs to be more than 0")
        User userId,
        @NotEmpty(message = "Event id is mandatory")
        @Size(min = 0, message = "Event id number needs to be more than 0")
        Event eventId


) {
}
