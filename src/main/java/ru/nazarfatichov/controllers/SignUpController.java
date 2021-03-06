/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.nazarfatichov.forms.SignUpForm;
import ru.nazarfatichov.services.SignUpService;

import javax.validation.Valid;

/**
 * @author nazar
 */
@Controller
public class SignUpController {

    @Autowired
    private SignUpService signUpService;

    @RequestMapping(path = "/signup", method = RequestMethod.GET)
    public String showSignUpPage() {
        return "signup";
    }

    @RequestMapping(path = "/signup", method = RequestMethod.POST)
    public String addUser(@Valid SignUpForm signUpForm,
                          BindingResult result,
                          ModelMap modelMap) {
        if (result.hasErrors()) {
            modelMap.addAttribute("errors", result.getAllErrors());
            return "signup";
        }
        signUpService.signUp(signUpForm);
        return "redirect:/signin";
    }

    @RequestMapping(path = "/admin/signup-teacher", method = RequestMethod.GET)
    public String showSignUpTeacher() {
        return "signup-teacher";
    }

    @RequestMapping(path = "/admin/signup-teacher", method = RequestMethod.POST)
    public String addTeacher(SignUpForm userForm) {
        signUpService.signUpteacher(userForm);
        return "redirect:/users";
    }

}
