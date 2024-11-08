package PaoloPellizzari.progettosettimana19.payloads;

import PaoloPellizzari.progettosettimana19.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record EventDTO(

        @NotEmpty(message = "Event date time is mandatory")
        LocalDateTime eventDt,
        @NotEmpty(message = "Title is mandatory")
        String title,
        String description,
        @NotEmpty(message = "Place is mandatory")
        String place,
        @NotEmpty(message = "Available seats are mandatory")
        @Size(min = 0, message = "Available seats number needs to be more than 0")
        int availableSeats,
        @NotEmpty(message = "Organizer id is mandatory")
        @Size(min = 0, message = "Organizer id number needs to be more than 0")
        User organizerId

) {
}
