package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.repositories.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationsService {

    @Autowired
    private ReservationsRepository reservationsRepo;
}
