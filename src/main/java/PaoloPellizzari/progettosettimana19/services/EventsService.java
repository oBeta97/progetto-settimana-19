package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.entities.Event;
import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.enums.UserRole;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.exceptions.NotFoundException;
import PaoloPellizzari.progettosettimana19.payloads.EventDTO;
import PaoloPellizzari.progettosettimana19.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepo;

    @Autowired
    private UsersService usersService;


    public Page<Event> getAll (int page, int size, String sortBy){
        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.eventsRepo.findAll(pageable);
    }

    public Event getById (long userId){
        return this.eventsRepo.findById(userId).orElseThrow(() -> new NotFoundException(Event.class));
    }


    public Event saveNewEvent(EventDTO newEventDTO){
        this.eventsRepo.findByPlaceAndEventDt(newEventDTO.place(), newEventDTO.eventDt()).ifPresent(
                user -> {
                    throw new BadRequestException("Il luogo " + newEventDTO.place() + " è già in uso in data " + newEventDTO.eventDt() + "!");
                }
        );

        User organizer = this.usersService.getById(newEventDTO.organizerId());

        if (organizer.getUserRole() != UserRole.ENVENT_PLANNER)
            throw new BadRequestException("L'utente deve esere un event planner!");

        Event newEvent = new Event(newEventDTO, organizer);
        return this.eventsRepo.save(newEvent);
    }

    public Event updateEvent(long idToUpdate, EventDTO newEventDTO){

        Event found = this.getById(idToUpdate);
        User u = this.usersService.getById(newEventDTO.organizerId());

        found.setEventDt(newEventDTO.eventDt());
        found.setPlace(newEventDTO.place());
        found.setTitle(newEventDTO.title());
        found.setDescription(newEventDTO.description());
        found.setAvailableSeats(newEventDTO.availableSeats());
        found.setOrganizerId(u);

        return this.eventsRepo.save(found);
    }


    public void deleteEvent(long idToDelete){
        this.eventsRepo.delete(
                this.getById(idToDelete)
        );
    }



}
