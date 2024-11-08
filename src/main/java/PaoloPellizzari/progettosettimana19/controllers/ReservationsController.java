package PaoloPellizzari.progettosettimana19.controllers;

import PaoloPellizzari.progettosettimana19.entities.Reservation;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.payloads.ReservationDTO;
import PaoloPellizzari.progettosettimana19.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    @Autowired
    private ReservationsService reservationsService;

    @GetMapping
    public Page<Reservation> getAllReservations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return this.reservationsService.getAll(page,size,sortBy);
    };

    @GetMapping("/{reservationId}")
    public Reservation getReservation (@PathVariable long reservationId){
        return this.reservationsService.getById(reservationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation saveNewReservation(@RequestBody @Validated ReservationDTO newReservationDTO, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.reservationsService.saveNewReservation(newReservationDTO);
    }


    @PutMapping("/{reservationId}")
    public Reservation updateReservation(@PathVariable long reservationId, @RequestBody @Validated ReservationDTO newReservationDTO, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.reservationsService.updateReservation(reservationId, newReservationDTO);
    }

    @DeleteMapping("/{reservationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReservation(@PathVariable long reservationId){

        this.reservationsService.deleteReservation(reservationId);

    }

}
