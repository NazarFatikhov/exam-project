/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.StudentExamTypeTask;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.models.UserInformation;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.StudentExamTypeTaskRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.StudentService;
import ru.nazarfatichov.transfer.UserDTO;
import ru.nazarfatichov.transfer.UserWithSubjectsDTO;

import javax.json.Json;

/**
 *
 * @author nazar
 */
@Controller
public class UsersController {
    
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;
    
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(Principal principal){
        Optional<User> user = usersRepository.findOneByEmailAdress(principal.getName());
        UserInformation userInformation = userInformationRepository.findFirstByUser_Id(user.get().getId());
        ModelAndView modelAndView = new ModelAndView("users");
        UserWithSubjectsDTO userWithSubjectsDTO = UserWithSubjectsDTO.builder()
                .userName(userInformation.getName())
                .userSurname(userInformation.getSurname())
                .studentSubjectInformation(studentService.getStudentSubjectInformation(user.get().getId()))
                .build();
        modelAndView.addObject("userDTO", userWithSubjectsDTO);
        return modelAndView;
    }

    @RequestMapping(path = "/teacher/exam/get-students", method = RequestMethod.POST)
    @ResponseBody
    public List<UserInformation> getStudentList(@RequestParam(name = "startString") String startString){
        return userInformationRepository.findAllByNameStartingWithAAndUser_Role(startString, Role.STUDENT);
    }
}