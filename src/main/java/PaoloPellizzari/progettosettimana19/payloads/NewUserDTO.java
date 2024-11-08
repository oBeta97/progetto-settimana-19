package PaoloPellizzari.progettosettimana19.payloads;

import PaoloPellizzari.progettosettimana19.enums.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUserDTO(
        String name,
        String surname,
        @NotEmpty(message = "Username is mandatory")
        String username,
        @NotEmpty(message = "Password is mandatory")
        @Size(min = 8, message = "Password needs to be at least 8 character long")
        String password,
        @NotEmpty(message = "User role is mandatory")
        UserRole userRole,
        @NotEmpty(message = "Email is mandatory")
        @Email(message = "Email not valid")
        String email

) {
}
