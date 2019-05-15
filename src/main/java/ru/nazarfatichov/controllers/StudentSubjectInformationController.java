package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.Subject;
import ru.nazarfatichov.services.StudentService;
import ru.nazarfatichov.services.SubjectService;

@Controller
public class StudentSubjectInformationController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @RequestMapping(path = "/admin/student-subject-information", method = RequestMethod.GET)
    public ModelAndView showStudentsSubjectInformation(){
        ModelAndView modelAndView = new ModelAndView("student-subject-information");
        modelAndView.addObject("subjectsWithTypes", subjectService.getAllExamsSubjectTypesFromServer());
        modelAndView.addObject("students", studentService.getAllStudentsFromServer());
        return modelAndView;
    }

    @RequestMapping(path = "/admin/student-subject-information", method = RequestMethod.POST)
    public String addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm){
        studentService.addStudentSubjectInformation(studentSubjectInformationForm);
        return "redirect:/admin/student-subject-information";
    }
}
