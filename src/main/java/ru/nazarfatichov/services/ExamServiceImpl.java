package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.forms.TestForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;
import ru.nazarfatichov.transfer.ExamDTO;

import java.text.ParseException;
import java.util.*;

@Service
public class ExamServiceImpl implements ExamService{

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private ExamsTasksRepository examsTasksRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExamMemberParser examMemberParser;

    @Autowired
    private UserMemberParser userMemberParser;

    @Override
    public Exam addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException {
        String studentName = userMemberParser.getUserName(examForm.getStudent());
        String studentSurname = userMemberParser.getUserSurname(examForm.getStudent());
        String teacherName = userMemberParser.getUserName(examForm.getTeacher());
        String teacherSurname = userMemberParser.getUserSurname(examForm.getTeacher());
        UserInformation studentInformation =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(studentName, studentSurname, Role.STUDENT);
        UserInformation teacherInformation =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(teacherName, teacherSurname, Role.TEACHER);
        User student = usersRepository.findOne(studentInformation.getUser().getId());
        User teacher = usersRepository.findOne(teacherInformation.getUser().getId());
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examForm.getTypeId());
        Date date = examMemberParser.parseDate(examForm.getDate());
        Integer[] scores = examForm.getScores();
        Integer totalScore = Arrays.stream(scores).mapToInt(Integer::intValue).sum();

        Exam exam = Exam.builder()
                .student(student)
                .teacher(teacher)
                .date(date)
                .examsSubjectsType(examsSubjectsType)
                .totalScore(totalScore)
                .build();

        saveExam(exam);

        saveExamTasks(exam, scores);

        studentService.updateStudentSubjectInformation(student, examsSubjectsType, exam);

        studentService.updateStudentTypeTasks(student, exam, scores);

        return exam;
    }

    @Override
    public ExamsTypeTask addExamsTypeTask(ExamTypeTaskForm examTypeTaskForm) {
        ExamsTypeTask examsTypeTask = ExamsTypeTask.builder()
                .examsSubjectsType(examsSubjectsTypeRepository.findOne(examTypeTaskForm.getTypeId()))
                .tasksNumber(examTypeTaskForm.getTaskNumber())
                .minScore(examTypeTaskForm.getMinScore())
                .maxScore(examTypeTaskForm.getMaxScore())
                .build();

        return examsTypeTaskRepository.save(examsTypeTask);
    }

    @Override
    public void saveExam(Exam exam) throws IncorrectSumOfTasksException{

        if(exam.getTotalScore() > exam.getExamsSubjectsType().getMaxScore() || exam.getTotalScore() < exam.getExamsSubjectsType().getMinScore()){
            throw new IncorrectSumOfTasksException();
        }

        examRepository.save(exam);
    }

    @Override
    public void saveExamTasks(Exam exam, Integer[] scores){
        ExamsTasks examsTasks = null;
        for (int i = 0; i < scores.length; i++) {
            examsTasks = ExamsTasks.builder()
                    .exam(exam)
                    .examsTypeTask(examsTypeTaskRepository.findFirstByTasksNumberAndExamsSubjectsType_Id(i + 1, exam.getExamsSubjectsType().getId()))
                    .score(scores[i])
                    .build();
            examsTasksRepository.save(examsTasks);
        }
    }

    @Override
    public void addTest(TestForm testForm, User teacher) throws ParseException, IncorrectSumOfTasksException {
        String studentName = userMemberParser.getUserName(testForm.getStudent());
        String studentSurname = userMemberParser.getUserSurname(testForm.getStudent());
        UserInformation studentInformation =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(studentName, studentSurname, Role.STUDENT);
        User student = usersRepository.findOne(studentInformation.getUser().getId());
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(testForm.getTypeId());
        Date date = examMemberParser.parseDate(testForm.getDate());

        Exam exam = Exam.builder()
                .date(date)
                .examsSubjectsType(examsSubjectsType)
                .teacher(teacher)
                .totalScore(testForm.getScore())
                .student(student)
                .build();

        saveExam(exam);

        studentService.updateStudentSubjectInformation(student, examsSubjectsType, exam);
    }

    @Override
    public Exam addExam(ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException {
        User student = usersRepository.findOne(examDTO.getStudentId());
        User teacher = usersRepository.findOne(examDTO.getTeacherId());
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examDTO.getTypeId());
        Date date = examMemberParser.parseDate(examDTO.getDate());
        Integer[] scores = examDTO.getScores();
        Integer totalScore = Arrays.stream(scores).mapToInt(Integer::intValue).sum();

        Exam exam = Exam.builder()
                .student(student)
                .teacher(teacher)
                .date(date)
                .examsSubjectsType(examsSubjectsType)
                .totalScore(totalScore)
                .build();

        saveExam(exam);

        saveExamTasks(exam, scores);

        studentService.updateStudentSubjectInformation(student, examsSubjectsType, exam);

        studentService.updateStudentTypeTasks(student, exam, scores);

        return exam;
    }

}
