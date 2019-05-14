package ru.nazarfatichov.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.security.details.UserDetailsImpl;

/**
 *
 * @author nazar
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private UsersRepository newUserRepository;
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        Optional<User> userCandidate = newUserRepository.findOneByEmailAdress(string);
        if(userCandidate.isPresent()){
            return new UserDetailsImpl((userCandidate.get()));
        }else{
            throw new IllegalArgumentException("User not found");
        }
       
    }
    
}
