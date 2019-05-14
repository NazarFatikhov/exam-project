/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.controllers;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.UsersRepository;

/**
 *
 * @author nazar
 */
@Controller
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;
    
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(Principal principal){
        Optional<User> user = usersRepository.findOneByEmailAdress(principal.getName());
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("userName", user.get().getUserInformation().getName());
        modelAndView.addObject("userSurname", user.get().getUserInformation().getSurname());
        return modelAndView;
    }
}
