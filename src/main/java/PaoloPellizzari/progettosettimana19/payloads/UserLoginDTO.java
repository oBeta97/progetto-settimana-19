package PaoloPellizzari.progettosettimana19.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserLoginDTO (

        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Email not valid")
        String email,
        @NotEmpty(message = "Password is mandatory")
        @Size(min = 8, message = "Password needs to be at least 8 character long")
        String password

) {}
