package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;

}
