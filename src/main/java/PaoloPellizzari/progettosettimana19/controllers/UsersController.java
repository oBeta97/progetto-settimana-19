package PaoloPellizzari.progettosettimana19.controllers;


import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.payloads.NewUserDTO;
import PaoloPellizzari.progettosettimana19.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public Page<User> getAllUsers(
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "id") String sortBy
                                ){
        return this.usersService.getAll(page,size,sortBy);
    };

    @GetMapping("/{userId}")
    public User getUser (@PathVariable long userId){
        return this.usersService.getById(userId);
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable long userId, @RequestBody @Validated NewUserDTO newUserDTO, BindingResult validationResult){
        if (validationResult.hasErrors()) {
            validationResult.getAllErrors().forEach(System.out::println);
            throw new BadRequestException("Ci sono stati errori nel payload!");
        }

        return this.usersService.updateUser(userId, newUserDTO);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable long userId){

        this.usersService.deleteUser(userId);

    }



}
