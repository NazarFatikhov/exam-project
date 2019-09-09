package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.exam.ExamService;
import ru.nazarfatichov.services.student.StudentService;
import ru.nazarfatichov.services.subject.SubjectService;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class ExamController {

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @RequestMapping(path = "/teacher/exam/new-exam", method = RequestMethod.GET)
    public String showNewExamPage(ModelMap modelMap){
        modelMap.addAttribute("studentInformations", userInformationRepository.findAllByUser_Role(Role.STUDENT).subList(0, 3));
        modelMap.addAttribute("teacherInformations", userInformationRepository.findAllByUser_Role(Role.TEACHER).subList(0, 3));
        modelMap.addAttribute("examsSubjectsTypes", examsSubjectsTypeRepository.findAll());
        return "new-exam";
    }

    @RequestMapping(path = "/teacher/exam/new-exam", method = RequestMethod.POST)
    public String saveNewExam(ModelMap modelMap, @Valid ExamForm examForm, BindingResult result) throws IncorrectSumOfTasksException, ParseException {
        if(result.hasErrors()){
            modelMap.addAttribute("errors", result.getAllErrors());
            return showNewExamPage(modelMap);
        }
        examService.addExam(examForm);

        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(ExamController.class, "showNewExamPage", new ModelMap()).build();
    }


}
