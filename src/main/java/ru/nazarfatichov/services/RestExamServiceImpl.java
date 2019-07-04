package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.models.ExamsTasks;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.models.UserInformation;
import ru.nazarfatichov.repositories.ExamRepository;
import ru.nazarfatichov.repositories.ExamsTasksRepository;
import ru.nazarfatichov.repositories.UserInformationRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.mappers.ExamMapper;
import ru.nazarfatichov.transfer.ExamDTO;
import ru.nazarfatichov.transfer.RestExamDto;
import ru.nazarfatichov.transfer.RestExamSubjectTypeDto;
import ru.nazarfatichov.transfer.RestUserDto;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class RestExamServiceImpl implements RestExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExamMemberParser examMemberParser;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsTasksRepository examsTasksRepository;

    @Override
    public List<RestExamDto> getAllExamDtos () {
        List<Exam> exams = examRepository.findAll();
        List<RestExamDto> restExamDtos = new ArrayList<>();
        for(Exam exam : exams){
            UserInformation teacherInformation = userInformationRepository.findFirstByUser_Id(exam.getTeacher().getId());
            UserInformation studentInformation = userInformationRepository.findFirstByUser_Id(exam.getStudent().getId());
            List<ExamsTasks> examsTasks = examsTasksRepository.findAllByExam_Id(exam.getId());

            ExamMapper mapper = new ExamMapper();
            restExamDtos.add(mapper.getExamDto(exam, studentInformation, teacherInformation, examsTasks));
        }

        return restExamDtos;
    }

    @Override
    public RestExamDto getExamDtoById(Long id) {
        Exam exam = examRepository.findOne(id);
        UserInformation teacherInformation = userInformationRepository.findFirstByUser_Id(exam.getTeacher().getId());
        UserInformation studentInformation = userInformationRepository.findFirstByUser_Id(exam.getStudent().getId());
        List<ExamsTasks> examsTasks = examsTasksRepository.findAllByExam_Id(exam.getId());
        if(exam == null){
            throw new IllegalArgumentException("Incorrect exam id");
        }

        ExamMapper mapper = new ExamMapper();
        return mapper.getExamDto(exam, studentInformation, teacherInformation, examsTasks);

    }

    @Override
    public Exam updateExam(Long id, ExamDTO examDTO) throws ParseException {
        Exam oldExamCandidate = examRepository.findOne(id);
        User student = usersRepository.getOne(examDTO.getStudentId());
        if(examRepository.findOne(id) == null) {
            throw new EntityNotFoundException("exam");
        } else if(usersRepository.getOne(examDTO.getStudentId()) == null){
            throw new EntityNotFoundException("student");
        } else if(usersRepository.getOne(examDTO.getTeacherId()) == null){
            throw new EntityNotFoundException("teacher");
        }
        Date date = examMemberParser.parseDate(examDTO.getDate());
        Integer totalScore = Arrays.stream(examDTO.getScores()).mapToInt(Integer::intValue).sum();
        examRepository.updateExam(examDTO.getStudentId(), examDTO.getTeacherId(),
                examDTO.getTypeId(), date, totalScore, oldExamCandidate.getId());
        return examRepository.findOne(id);
    }

    @Override
    public Exam addNewExam(ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException {
        return examService.addExam(examDTO);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.delete(id);
    }
}
