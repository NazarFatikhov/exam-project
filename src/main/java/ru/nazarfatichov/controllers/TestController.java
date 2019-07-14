package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.TestForm;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.ExamService;

import javax.validation.Valid;
import java.security.Principal;
import java.text.ParseException;
import java.util.Optional;


@Controller
public class TestController {

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private UsersRepository usersRepository;

    @RequestMapping(path = "/teacher/exam/new-test", method = RequestMethod.GET)
    public String getAddNewTestPage(ModelMap modelMap){
        modelMap.addAttribute("studentInformations", userInformationRepository.findAllByUser_Role(Role.STUDENT));
        modelMap.addAttribute("examsSubjectsTypes", examsSubjectsTypeRepository.findAll());
        return "new-test";
    }

    @RequestMapping(path = "/teacher/exam/new-test", method = RequestMethod.POST)
    public String addNewTest(@Valid TestForm testForm, Principal principal, BindingResult bindingResult, ModelMap modelMap){
        Optional<User> teacher = usersRepository.findOneByEmailAdress(principal.getName());
        try {
            if(!bindingResult.hasErrors()) {
                examService.addTest(testForm, teacher.get());
                getAddNewTestPage(modelMap);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IncorrectSumOfTasksException e) {
            e.printStackTrace();
        }
        return "redirect:/teacher/exam/new-test";
    }
}
