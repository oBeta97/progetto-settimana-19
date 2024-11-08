package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.exceptions.NotFoundException;
import PaoloPellizzari.progettosettimana19.payloads.NewUserDTO;
import PaoloPellizzari.progettosettimana19.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepo;

    @Autowired
    private PasswordEncoder bcrypt;

    public Page<User> getAll (int page, int size, String sortBy){
        if(size > 10) size = 10;

        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return this.usersRepo.findAll(pageable);
    }

    public User getById (long userId){
        return this.usersRepo.findById(userId).orElseThrow(() -> new NotFoundException(User.class));
    }

    public User getByEmail(String email){
        return this.usersRepo.findByEmail(email).orElseThrow(() -> new NotFoundException(User.class));
    }

    public User saveNewUser(NewUserDTO newUserDTO){
        this.usersRepo.findByEmail(newUserDTO.email()).ifPresent(
                // 1.1 Se trovo uno user con quell'indirizzo triggera un errore
                user -> {
                    throw new BadRequestException("Email " + newUserDTO.email() + " già in uso!");
                }
        );

        User newUser = new User(newUserDTO);
        newUser.setPassword(bcrypt.encode(newUser.getPassword()));

        return this.usersRepo.save(newUser);
    }

    public User updateUser(long idToUpdate, NewUserDTO newUserDTO){

        User found = this.getById(idToUpdate);

        found.setEmail(newUserDTO.email());
        found.setName(newUserDTO.name());
        found.setSurname(newUserDTO.surname());
        found.setUsername(newUserDTO.username());
        found.setUserRole(newUserDTO.userRole());
        // TODO - creare un setPassword con l'hashing

        return this.usersRepo.save(found);
    }


    public void deleteUser(long idToDelete){
        this.usersRepo.delete(
                this.getById(idToDelete)
        );
    }


}
