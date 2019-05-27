package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.ExamService;
import ru.nazarfatichov.services.StudentService;
import ru.nazarfatichov.services.SubjectService;

import javax.persistence.criteria.CriteriaBuilder;
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

    @RequestMapping(path = "/teacher/exam/{exams-subjects-type-id}", method = RequestMethod.GET)
    public ModelAndView showExamPage(@PathVariable("exams-subjects-type-id") Long examsSubjectTypeId){
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examsSubjectTypeId);
        ModelAndView modelAndView = new ModelAndView("exam");
        modelAndView.addObject("taskCount", examsSubjectsType.getTasksCount());
        modelAndView.addObject("studentsInformation", userInformationRepository.findAllByUser_Role(Role.STUDENT));
        modelAndView.addObject("teachersInformation", userInformationRepository.findAllByUser_Role(Role.TEACHER));
        modelAndView.addObject("examsSubjectsTypes" , subjectService.getAllExamsSubjectTypesFromServer());
        return modelAndView;
    }

    @RequestMapping(path = "/teacher/exam/3", method = RequestMethod.POST)
    public String addExam(ExamForm examForm){
        try {
            examService.addExam(examForm);
        } catch (IncorrectSumOfTasksException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "redirect:/teacher/exam/3";
    }
}
