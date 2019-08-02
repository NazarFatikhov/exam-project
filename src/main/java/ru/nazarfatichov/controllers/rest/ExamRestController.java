package ru.nazarfatichov.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.services.exam.RestExamService;
import ru.nazarfatichov.transfer.ExamDTO;
import ru.nazarfatichov.transfer.ExceptionDTO;
import ru.nazarfatichov.transfer.RestExamDto;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;

@Controller
public class ExamRestController {

    @Autowired
    private RestExamService restExamService;

    @ResponseBody
    @ExceptionHandler({IncorrectSumOfTasksException.class, ParseException.class,
            DataIntegrityViolationException.class, IllegalArgumentException.class})
    public ExceptionDTO handleException(Exception ex){
        return new ExceptionDTO(ex.getClass().getName(), ex.getMessage());
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.GET)
    @ResponseBody
    public List<RestExamDto> getAllExams(){
        return restExamService.getAllExamDtos();
    }

    @RequestMapping(path = "/api/exam/{id}")
    @ResponseBody
    public RestExamDto getOneExamById(@PathVariable("id") Long id){
        return restExamService.getExamDtoById(id);
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Exam createNewExam(@RequestBody ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException {
        return restExamService.addNewExam(examDTO);
    }

    @RequestMapping(path = "/api/exam/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Exam changeExam(@PathVariable("id") Long examId, @RequestBody ExamDTO examDTO) throws ParseException {
        try{
            return restExamService.updateExam(examId, examDTO);
        } catch (EntityNotFoundException ex){
            throw new IllegalArgumentException(ex.getMessage() + " not found");
        }
    }

    @RequestMapping(path = "/api/exam/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteExam(@PathVariable("id") Long examId){
        restExamService.deleteExam(examId);
    }
}
