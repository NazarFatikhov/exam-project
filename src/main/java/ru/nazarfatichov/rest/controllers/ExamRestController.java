package ru.nazarfatichov.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.repositories.ExamRepository;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.ExamMemberParser;
import ru.nazarfatichov.services.ExamService;
import ru.nazarfatichov.services.UserMemberParser;
import ru.nazarfatichov.transfer.ExamDTO;
import ru.nazarfatichov.transfer.ExceptionDTO;

import javax.persistence.EntityExistsException;
import javax.swing.text.html.parser.Entity;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Controller
public class ExamRestController {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private ExamMemberParser examMemberParser;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private UsersRepository usersRepository;

    @ResponseBody
    @ExceptionHandler({IncorrectSumOfTasksException.class, ParseException.class})
    public ExceptionDTO handleException(Exception ex){
        return new ExceptionDTO(ex.getClass().getName(), ex.getMessage());
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.GET)
    @ResponseBody
    public List<Exam> getAllExams(){
        return examRepository.findAll();
    }

    @RequestMapping(path = "/api/exam/{id}")
    @ResponseBody
    public Exam getOneExamById(@PathVariable("id") Long id){
        Exam examCandidate = examRepository.findOne(id);
        if(examCandidate == null){
            throw new IllegalArgumentException("Incorrect exam id");
        }
        return examCandidate;
    }

    @RequestMapping(path = "/api/exam", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public Exam createNewExam(@RequestBody ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException {
        return examService.addExam(examDTO);
    }

    @RequestMapping(path = "/api/exam/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Exam changeExam(@PathVariable("id") Long examId, @RequestBody ExamDTO examDTO) throws ParseException {
        Exam oldExamCandidate = examRepository.findOne(examId);
        if(oldExamCandidate == null){
            throw new IllegalArgumentException("Incorrect exam id");
        }
        Exam changedExam = Exam.builder()
                .id(oldExamCandidate.getId())
                .date(examMemberParser.parseDate(examDTO.getDate()))
                .examsSubjectsType(examsSubjectsTypeRepository.findOne(examDTO.getTypeId()))
                .teacher(usersRepository.findOne(examDTO.getTeacherId()))
                .student(usersRepository.findOne(examDTO.getStudentId()))
                .totalScore(Arrays.stream(examDTO.getScores()).mapToInt(Integer::intValue).sum())
                .build();
        return changedExam;
    }
}
