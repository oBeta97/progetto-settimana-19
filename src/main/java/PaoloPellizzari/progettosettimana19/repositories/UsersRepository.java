package PaoloPellizzari.progettosettimana19.repositories;

import PaoloPellizzari.progettosettimana19.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {
}
