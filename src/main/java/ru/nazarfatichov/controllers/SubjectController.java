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
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.services.SubjectService;

/**
 *
 * @author nazar
 */
@Controller
public class SubjectController {
    
    @Autowired
    SubjectService subjectService;
    
    @RequestMapping(path = "/admin/subjects", method = RequestMethod.GET)
    public ModelAndView showAllSubjects(){
        ModelAndView modelAndView = new ModelAndView("subjects");
        modelAndView.addObject("subjectsFromServer", subjectService.getAllSubjects());
        return modelAndView;
    }
    
    @RequestMapping(path = "/admin/add-subject", method = RequestMethod.POST)
    public String addSubject(SubjectForm subjectForm){
        subjectService.addSubject(subjectForm);
        return "redirect:/admin/subjects";
    }
    
}
