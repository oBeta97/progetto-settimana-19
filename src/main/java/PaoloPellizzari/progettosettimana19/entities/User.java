package PaoloPellizzari.progettosettimana19.entities;


import PaoloPellizzari.progettosettimana19.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String surname;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    public User(String name, String surname, String username, String password) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.userRole = UserRole.CUSTOMER;
    }
}
