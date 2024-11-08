package PaoloPellizzari.progettosettimana19.entities;


import PaoloPellizzari.progettosettimana19.enums.UserRole;
import PaoloPellizzari.progettosettimana19.payloads.NewUserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String name, String surname, String username, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.userRole = UserRole.CUSTOMER;
        this.email = email;
    }

    public User(NewUserDTO newUserDTO){
        this.name = newUserDTO.name();
        this.surname = newUserDTO.surname();
        this.username = newUserDTO.username();
        this.password = newUserDTO.password();
        this.userRole = UserRole.CUSTOMER;
        this.email = newUserDTO.email();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.userRole.name()));
    }


}
