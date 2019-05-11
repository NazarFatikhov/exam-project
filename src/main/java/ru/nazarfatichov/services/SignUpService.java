package ru.nazarfatichov.services;

import ru.nazarfatichov.forms.SignUpForm;

/**
 *
 * @author nazar
 */
public interface SignUpService {
    public void signUp(SignUpForm signUpForm);
    
    public void signUpteacher(SignUpForm signUpForm);
}
