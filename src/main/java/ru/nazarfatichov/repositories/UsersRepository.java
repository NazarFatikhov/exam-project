package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.models.User;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author nazar
 */
public interface UsersRepository extends JpaRepository<User, Long>{

    Optional<User> findOneByEmailAdress(String emailAdress);
    
    List<User> findAllByEmailAdress(String emailAdress);
    List<User> findAllByRole(Role role);

}
