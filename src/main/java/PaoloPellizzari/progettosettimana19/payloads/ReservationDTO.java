package PaoloPellizzari.progettosettimana19.payloads;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ReservationDTO(

        @NotNull(message = "Reservation date time is mandatory")
        LocalDate reservationDt,
        @NotNull(message = "User id is mandatory")
//        @Size(min = 0, message = "User id number needs to be more than 0")
        long userId,
        @NotNull(message = "Event id is mandatory")
//        @Size(min = 0, message = "Event id number needs to be more than 0")
        long eventId


) {
}
