package PaoloPellizzari.progettosettimana19.controllers;


import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.exceptions.BadRequestException;
import PaoloPellizzari.progettosettimana19.payloads.NewUserDTO;
import PaoloPellizzari.progettosettimana19.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser (@RequestBody @Validated NewUserDTO body, BindingResult validationResult){

        if (validationResult.hasErrors()) {
            String message = validationResult.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage())
                    .collect(Collectors.joining(". "));
            throw new BadRequestException("Ci sono stati errori nel payload! " + message);
        }

        return this.usersService.saveNewUser(body);
    }

}
