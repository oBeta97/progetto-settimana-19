package PaoloPellizzari.progettosettimana19.controllers;


import PaoloPellizzari.progettosettimana19.entities.Event;
import PaoloPellizzari.progettosettimana19.entities.Event;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.payloads.EventDTO;
import PaoloPellizzari.progettosettimana19.services.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/events")
public class EventsController {
    @Autowired
    private EventsService eventsService;

    @GetMapping
    public Page<Event> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy
    ){
        return this.eventsService.getAll(page,size,sortBy);
    };

    @GetMapping("/{eventId}")
    public Event getEvent (@PathVariable long eventId){
        return this.eventsService.getById(eventId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ENVENT_PLANNER')")
    public Event saveNewEvent(@RequestBody @Validated EventDTO newEventDTO, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.eventsService.saveNewEvent(newEventDTO);
    }


    @PutMapping("/{eventId}")
    @PreAuthorize("hasAuthority('ENVENT_PLANNER')")
    public Event updateEvent(@PathVariable long eventId, @RequestBody @Validated EventDTO newEventDTO, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.eventsService.updateEvent(eventId, newEventDTO);
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ENVENT_PLANNER')")
    public void deleteEvent(@PathVariable long eventId){

        this.eventsService.deleteEvent(eventId);

    }

}
