package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;
import ru.nazarfatichov.forms.SignUpForm;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.models.UserInformation;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;

/**
 *
 * @author nazar
 */
@Service
public class SignUpServiceImpl implements SignUpService{
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsersRepository userRepository;
    @Autowired
    private UserInformationRepository userInformationRepository;
    
    @Override
    public void signUp(SignUpForm signUpForm) {
        
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());
        
        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .build();
        
        userInformationRepository.save(userInformation);
        
        User user = User.builder()
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.STUDENT)
                .state(UserState.ACTIVE)
                .userInformation(userInformation)
                .build();
        
        userRepository.save(user);
  
    }

    @Override
    public void signUpteacher(SignUpForm signUpForm) {
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());
        
        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .build();
        
        userInformationRepository.save(userInformation);

        User user = User.builder()
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.TEACHER)
                .state(UserState.ACTIVE)
                .userInformation(userInformation)
                .build();
        
        userRepository.save(user);
    }
    
}
