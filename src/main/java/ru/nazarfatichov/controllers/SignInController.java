package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.nazarfatichov.forms.UserForm;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.UsersRepository;

/**
 *
 * @author nazar
 */
@Controller
public class SignInController {
    
    @RequestMapping(path = "/signin", method = RequestMethod.GET)
    public String showSignInPage(UserForm userForm) {
        return "signin";
    }
    
    
    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public String signIn(Authentication authentication, ModelMap modelMap){
        if(authentication != null){
            return "redirect:/";
        }
        return "login";
        
            
    }
}
