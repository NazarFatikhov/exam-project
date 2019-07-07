package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;
import ru.nazarfatichov.services.mappers.ExamMapper;
import ru.nazarfatichov.transfer.RestExamDto;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RestExamServiceImpl implements RestExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsTasksRepository examsTasksRepository;

    @Autowired
    private ExamMemberParser examMemberParser;

    @Override
    public List<RestExamDto> getAllExamDtos() {
        List<Exam> exams = examRepository.findAll();
        List<RestExamDto> restExamDtos = new ArrayList<>();
        for (Exam exam : exams) {
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
        if (exam == null) {
            throw new IllegalArgumentException("Incorrect exam id");
        }

        ExamMapper mapper = new ExamMapper();
        return mapper.getExamDto(exam, studentInformation, teacherInformation, examsTasks);

    }

    @Override
    public RestExamDto updateExam(Long id, RestExamDto examDTO) throws ParseException {
        Exam oldExam = examRepository.findOne(id);
        Optional<User> studentCandidate = usersRepository.findOneByEmailAdress(examDTO.getStudent().getEmail());
        Optional<User> teacherCandidate = usersRepository.findOneByEmailAdress(examDTO.getTeacher().getEmail());
        Optional<ExamsSubjectsType> examsSubjectsTypeCandidate = examsSubjectsTypeRepository.findFirstByTypeAndAndSubject_Name(
                Type.valueOf(examDTO.getType().getTypeName()), examDTO.getType().getSubjectName());
        if (oldExam == null) {
            throw new EntityNotFoundException("exam");
        } else if (!studentCandidate.isPresent()) {
            throw new EntityNotFoundException("student");
        } else if (!teacherCandidate.isPresent()) {
            throw new EntityNotFoundException("teacher");
        } else if (!examsSubjectsTypeCandidate.isPresent()) {
            throw new EntityNotFoundException("type");
        }
        Date date = examMemberParser.parseDateWithHours(examDTO.getDate());
        examRepository.updateExam(studentCandidate.get().getId(), teacherCandidate.get().getId(),
                examsSubjectsTypeCandidate.get().getId(), date, examDTO.getTotalScore(), oldExam.getId());
        ExamMapper examMapper = new ExamMapper();
        Exam newExam = examRepository.findOne(id);
        return examMapper.getExamDto(newExam, userInformationRepository.findFirstByUser_Id(studentCandidate.get().getId()), userInformationRepository.findFirstByUser_Id(teacherCandidate.get().getId()), examsTasksRepository.findAllByExam_Id(newExam.getId()));
    }

    @Override
    public Exam addNewExam(RestExamDto examDTO) throws IncorrectSumOfTasksException, ParseException {
        return examService.addExam(examDTO);
    }

    @Override
    public void deleteExam(Long id) {
        examRepository.delete(id);
    }
}
