package ru.nazarfatichov.controllers;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;

/**
 *
 * @author nazar
 */
@Controller
public class ExamsSubjectsTypeController {

    @Autowired
    ExamsSubjectsTypeRepository examsSubjectsTypeRepository;
    
    @RequestMapping("/admin/exams-subjects-type")
    public ModelAndView showAllExamsSubjectsTypes(){
        ModelAndView modelAndView = new ModelAndView("exams-subjects-type");
        modelAndView.addObject("typesFromServer", Arrays.asList(Type.values()));
        return modelAndView;
    }
}
