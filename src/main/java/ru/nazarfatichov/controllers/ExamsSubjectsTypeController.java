package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.services.subject.SubjectService;
import ru.nazarfatichov.transfer.ExamsSubjectsTypeDTO;

import java.util.Arrays;

/**
 *
 * @author nazar
 */
@Controller
public class ExamsSubjectsTypeController {

    @Autowired
    private SubjectService subjectService;


    @RequestMapping(value = "/admin/exams-subjects-type", method = RequestMethod.GET)
    public ModelAndView showAllExamsSubjectsTypes(){
        ExamsSubjectsTypeDTO examsSubjectsTypesDTO = ExamsSubjectsTypeDTO.builder()
                .examsSubjectsTypes(subjectService.getAllExamsSubjectTypesFromServer())
                .subjects(subjectService.getAllSubjects())
                .types(Arrays.asList(Type.values()))
                .build();
        ModelAndView modelAndView = new ModelAndView("exams-subjects-type");
        modelAndView.addObject("examsSubjectsTypeDTO", examsSubjectsTypesDTO);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/exams-subjects-type", method = RequestMethod.POST)
    public String addExamsSubjectsTypes(ExamsSubjectsTypesForm examsSubjectsTypesForm){
        subjectService.addExamsSubjectsType(examsSubjectsTypesForm);
        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(ExamsSubjectsTypeController.class, "showAllExamsSubjectsTypes").build();
    }


}
