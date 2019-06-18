package ru.nazarfatichov.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.repositories.ExamRepository;
import ru.nazarfatichov.services.ExamService;
import ru.nazarfatichov.transfer.ExamDTO;

import javax.persistence.EntityExistsException;
import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.util.List;

@Controller
public class ExamRestController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamService examService;

    @ResponseBody
    @ExceptionHandler({IncorrectSumOfTasksException.class, ParseException.class})
    public String handleException(Exception ex){
        return ex.getMessage();
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.GET)
    @ResponseBody
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    @RequestMapping(path = "/api/exam/{id}")
    @ResponseBody
    public Exam getOneExamById(@PathVariable("id") Long id){
        return examRepository.findOne(id);
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Exam createNewExam(@RequestBody ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException {
        return examService.addExam(examDTO);
    }
}
