package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.services.student.StudentService;
import ru.nazarfatichov.services.subject.SubjectService;

import java.util.List;

@Controller
public class StudentSubjectInformationController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    StudentService studentService;

    @RequestMapping(path = "/admin/student-subject-information", method = RequestMethod.GET)
    public String showStudentsSubjectInformation(ModelMap modelMap){
        List<ExamsSubjectsType> examsSubjectsTypes = subjectService.getAllExamsSubjectTypesFromServer();
        List<User> students = studentService.getAllStudentsFromServer();
        modelMap.addAttribute("students", students);
        modelMap.addAttribute("examsSubjectsTypes", examsSubjectsTypes);
        return "studentSubjectInformation";
    }

    @RequestMapping(path = "/admin/student-subject-information", method = RequestMethod.POST)
    public String addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm){
        studentService.addStudentSubjectInformation(studentSubjectInformationForm);
        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(StudentSubjectInformationController.class, "showStudentsSubjectInformation", new ModelMap()).build();
    }
}
