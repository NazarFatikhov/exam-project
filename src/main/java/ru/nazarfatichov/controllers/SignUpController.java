/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.nazarfatichov.forms.SignUpForm;
import ru.nazarfatichov.forms.UserForm;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.SignUpService;

/**
 *
 * @author nazar
 */
@Controller
public class SignUpController {
    
    @Autowired
    SignUpService signUpService;
    
    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String showSignUpPage() {
        return "signup";
    }
    
    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String addUser(SignUpForm userForm) {
        signUpService.signUp(userForm);
        return "redirect:/signin";
    }
    
    @RequestMapping(path = "/admin/signup-teacher", method = RequestMethod.GET)
    public String showSignUpTeacher(){
        return "signup-teacher";
    }
    
    @RequestMapping(path = "/admin/signup-teacher", method = RequestMethod.POST)
    public String addTeacher(SignUpForm userForm){
        signUpService.signUpteacher(userForm);
        return "redirect:/signin";
    }
    
}
