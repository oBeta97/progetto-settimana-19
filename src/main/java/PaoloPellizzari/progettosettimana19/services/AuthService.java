package PaoloPellizzari.progettosettimana19.services;

import PaoloPellizzari.progettosettimana19.entities.User;
import PaoloPellizzari.progettosettimana19.exceptions.UnauthorizedException;
import PaoloPellizzari.progettosettimana19.payloads.UserLoginDTO;
import PaoloPellizzari.progettosettimana19.tools.JWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UsersService usersService;

    @Autowired
    private JWT jwt;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        User found = this.usersService.getByEmail(body.email());
        if (bcrypt.matches(body.password(), found.getPassword())) {
            return jwt.createToken(found);
        } else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }

}