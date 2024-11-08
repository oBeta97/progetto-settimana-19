package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.entities.Event;
import PaoloPellizzari.progettosettimana19.entities.Reservation;
import PaoloPellizzari.progettosettimana19.entities.Reservation;
import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.enums.UserRole;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.exceptions.NotFoundException;
import PaoloPellizzari.progettosettimana19.payloads.ReservationDTO;
import PaoloPellizzari.progettosettimana19.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepo;

    @Autowired
    private UsersService usersService;

    @Autowired
    private EventsService eventsService;


    public Page<Reservation> getAll (int page, int size, String sortBy){
        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.reservationsRepo.findAll(pageable);
    }

    public Reservation getById (long userId){
        return this.reservationsRepo.findById(userId).orElseThrow(() -> new NotFoundException(Reservation.class));
    }


    public Reservation saveNewReservation(ReservationDTO newReservationDTO){

        Event e = this.eventsService.getById(newReservationDTO.eventId());
        User u = this.usersService.getById(newReservationDTO.userId());

        this.reservationsRepo.findReservationByUserIdAndEventDt(u.getId(), e.getEventDt()).ifPresent(
                user -> {
                    throw new BadRequestException("l'utente è già occupato in quella data");
                }
        );

        Reservation newReservation = new Reservation(newReservationDTO.reservationDt(), u, e);
        return this.reservationsRepo.save(newReservation);
    }

    public Reservation updateReservation(long idToUpdate, ReservationDTO newReservationDTO){

        Reservation found = this.getById(idToUpdate);
        User u = this.usersService.getById(newReservationDTO.userId());
        Event e = this.eventsService.getById(newReservationDTO.eventId());


        found.setReservationDt(newReservationDTO.reservationDt());
        found.setUserId(u);
        found.setEventId(e);

        return this.reservationsRepo.save(found);
    }


    public void deleteReservation(long idToDelete){
        this.reservationsRepo.delete(
                this.getById(idToDelete)
        );
    }



}
