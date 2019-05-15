package ru.nazarfatichov.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.models.User;

/**
 *
 * @author nazar
 */
public interface UsersRepository extends JpaRepository<User, Long>{
    Optional<User> findOneByEmailAdress(String emailAdress);
    
    List<User> findAllByEmailAdress(String emailAdress);
    List<User> findAllByRole(Role role);

}
