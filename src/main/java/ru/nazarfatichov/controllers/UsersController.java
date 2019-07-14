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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.models.*;
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
    
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ModelAndView getUsers(Principal principal){
        Optional<User> user = usersRepository.findOneByEmailAdress(principal.getName());
        Optional<UserInformation> userInformationCandidate = userInformationRepository.findFirstByUser_Id(user.get().getId());
        ModelAndView modelAndView = new ModelAndView("users");
        UserWithSubjectsDTO userWithSubjectsDTO = UserWithSubjectsDTO.builder()
                .userName(userInformationCandidate.get().getName())
                .userSurname(userInformationCandidate.get().getSurname())
                .studentSubjectInformation(studentService.getStudentSubjectInformation(user.get().getId()))
                .build();
        modelAndView.addObject("userDTO", userWithSubjectsDTO);
        modelAndView.addObject("isTeacher", user.get().getRole().equals(Role.TEACHER));
        modelAndView.addObject("isAdmin", user.get().getRole().equals(Role.ADMIN));
        return modelAndView;
    }

    @RequestMapping(path = "/teacher/exam/get-students", method = RequestMethod.POST)
    @ResponseBody
    public List<UserInformation> getStudentList(@RequestParam(name = "startString") String startString){
        return userInformationRepository.findAllByNameStartingWithAndUser_Role(startString, Role.STUDENT);
    }

    @RequestMapping(path = "/teacher/exam/get-teachers", method = RequestMethod.POST)
    @ResponseBody
    public List<UserInformation> getTeacherList(@RequestParam(name = "startString") String startString){
        return userInformationRepository.findAllByNameStartingWithAndUser_Role(startString, Role.TEACHER);
    }

    @RequestMapping(path = "/teacher/exam/get-students", method = RequestMethod.GET)
    @ResponseBody
    public List<UserInformation> getStudentList2(){
        return userInformationRepository.findAll();
    }

}