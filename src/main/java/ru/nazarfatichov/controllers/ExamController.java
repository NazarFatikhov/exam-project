package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.ExamService;
import ru.nazarfatichov.services.StudentService;
import ru.nazarfatichov.services.SubjectService;

import javax.jws.WebParam;
import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;

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

    @RequestMapping(path = "/teacher/exam/new", method = RequestMethod.GET)
    public String showExamPage(ModelMap modelMap){
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(Long.valueOf(3));//Change to really view
        modelMap.addAttribute("taskCount", examsSubjectsType.getTasksCount());
        modelMap.addAttribute("studentsInformation", userInformationRepository.findAllByUser_Role(Role.STUDENT));
        modelMap.addAttribute("teachersInformation", userInformationRepository.findAllByUser_Role(Role.TEACHER));
        modelMap.addAttribute("examsSubjectsTypes" , subjectService.getAllExamsSubjectTypesFromServer());
        return "exam";
    }

    @RequestMapping(path = "/teacher/exam/new", method = RequestMethod.POST)
    public String addExam(@Valid ExamForm examForm, BindingResult result, ModelMap modelMap){
        try {
            if(result.hasErrors()){
                modelMap.addAttribute("errors", result.getAllErrors());
                return showExamPage(modelMap);
            }
            examService.addExam(examForm);
        } catch (IncorrectSumOfTasksException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/teacher/exam/new?success";
    }

    @RequestMapping(path = "/teacher/exam/new-exam", method = RequestMethod.GET)
    public String showNewExamPage(ModelMap modelMap){
        modelMap.addAttribute("studentInformations", userInformationRepository.findAllByUser_Role(Role.STUDENT));
        modelMap.addAttribute("teacherInformations", userInformationRepository.findAllByUser_Role(Role.TEACHER));
        return "new-exam";
    }


}
