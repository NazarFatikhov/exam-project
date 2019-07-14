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
        Optional<UserInformation> studentInformationCandidate =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(studentName, studentSurname, Role.STUDENT);
        Optional<UserInformation> teacherInformationCandidate =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(teacherName, teacherSurname, Role.TEACHER);
        Optional<User> studentCandidate = usersRepository.findById(studentInformationCandidate.get().getUser().getId());
        Optional<User> teacherCandidate = usersRepository.findById(teacherInformationCandidate.get().getUser().getId());
        Optional<ExamsSubjectsType> examsSubjectsTypeCandidate = examsSubjectsTypeRepository.findById(examForm.getTypeId());
        Date date = examMemberParser.parseDate(examForm.getDate());
        Integer[] scores = examForm.getScores();
        Integer totalScore = Arrays.stream(scores).mapToInt(Integer::intValue).sum();

        Exam exam = Exam.builder()
                .student(studentCandidate.get())
                .teacher(teacherCandidate.get())
                .date(date)
                .examsSubjectsType(examsSubjectsTypeCandidate.get())
                .totalScore(totalScore)
                .build();

        saveExam(exam);

        saveExamTasks(exam, scores);

        studentService.updateStudentSubjectInformation(studentCandidate.get(), examsSubjectsTypeCandidate.get(), exam);

        studentService.updateStudentTypeTasks(studentCandidate.get(), exam, scores);

        return exam;
    }

    @Override
    public ExamsTypeTask addExamsTypeTask(ExamTypeTaskForm examTypeTaskForm) {
        ExamsTypeTask examsTypeTask = ExamsTypeTask.builder()
                .examsSubjectsType(examsSubjectsTypeRepository.findById(examTypeTaskForm.getTypeId()).get())
                .tasksNumber(examTypeTaskForm.getTaskNumber())
                .minScore(examTypeTaskForm.getMinScore())
                .maxScore(examTypeTaskForm.getMaxScore())
                .build();

        ExamsTypeTask examsTypeTask1 = examsTypeTaskRepository.getOne(Long.valueOf("2"));
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
                    .examsTypeTask(examsTypeTaskRepository.findFirstByTasksNumberAndExamsSubjectsType_Id(i + 1, exam.getExamsSubjectsType().getId()).get())
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
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(studentName, studentSurname, Role.STUDENT).get();
        User student = usersRepository.findById(studentInformation.getUser().getId()).get();
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findById(testForm.getTypeId()).get();
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
        User student = usersRepository.findById(examDTO.getStudentId()).get();
        User teacher = usersRepository.findById(examDTO.getTeacherId()).get();
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findById(examDTO.getTypeId()).get();
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
