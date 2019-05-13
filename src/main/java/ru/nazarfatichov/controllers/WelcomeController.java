package ru.nazarfatichov.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author nazar
 */
@Controller
public class WelcomeController {
    
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showWelcomePage(){
        return "welcome";
    }
    
}
