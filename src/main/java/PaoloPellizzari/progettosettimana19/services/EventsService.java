package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.repositories.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepo;

}
