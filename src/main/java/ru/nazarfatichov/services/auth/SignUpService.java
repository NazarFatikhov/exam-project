package ru.nazarfatichov.services.auth;

import ru.nazarfatichov.forms.SignUpForm;

/**
 *
 * @author nazar
 */
public interface SignUpService {
    public void signUp(SignUpForm signUpForm);
    
    public void signUpTeacher(SignUpForm signUpForm);
}
