package ru.nazarfatichov.services;

import com.sun.javafx.collections.IntegerArraySyncer;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.SimpleFormatter;
import java.util.stream.IntStream;

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
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Override
    public void addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException {
        String studentName = getUserName(examForm.getStudent());
        String studentSurname = getUserSurname(examForm.getStudent());
        String teacherName = getUserName(examForm.getTeacher());
        String teacherSurname = getUserSurname(examForm.getTeacher());
        UserInformation studentInformation =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(studentName, studentSurname, Role.STUDENT);
        UserInformation teacherInformation =
                userInformationRepository.findFirstByNameAndSurnameAndUser_Role(teacherName, teacherSurname, Role.TEACHER);
        User student = usersRepository.findOne(studentInformation.getUser().getId());
        User teacher = usersRepository.findOne(teacherInformation.getUser().getId());
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examForm.getTypeId());
        Date date = parseDate(examForm);
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

        updateStudentSubjectInformation(student, examsSubjectsType, exam);

        updateStudentTypeTasks(student, exam, scores);
    }

    @Override
    public void addExamsTypeTask(ExamTypeTaskForm examTypeTaskForm) {
        ExamsTypeTask examsTypeTask = ExamsTypeTask.builder()
                .examsSubjectsType(examsSubjectsTypeRepository.findOne(examTypeTaskForm.getTypeId()))
                .tasksNumber(examTypeTaskForm.getTaskNumber())
                .minScore(examTypeTaskForm.getMinScore())
                .maxScore(examTypeTaskForm.getMaxScore())
                .build();

        examsTypeTaskRepository.save(examsTypeTask);
    }

    private void saveExam(Exam exam) throws IncorrectSumOfTasksException{

        if(exam.getTotalScore() > exam.getExamsSubjectsType().getMaxScore() || exam.getTotalScore() < exam.getExamsSubjectsType().getMinScore()){
            throw new IncorrectSumOfTasksException();
        }

        examRepository.save(exam);
    }

    private void saveExamTasks(Exam exam, Integer[] scores){
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

    private Date parseDate(ExamForm examForm) throws ParseException {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("YYYY-mm-dd");
        Date date = simpleFormatter.parse(examForm.getDate());

        return date;
    }

    private void updateStudentSubjectInformation(User student, ExamsSubjectsType examsSubjectsType, Exam exam){
        StudentSubjectInformation studentSubjectInformation =
                studentSubjectInformationRepository.findFirstByUser_IdAndExamsSubjectsType_Id(student.getId(), examsSubjectsType.getId());
        Integer examCount = studentSubjectInformation.getExamCount();
        if(examCount == null){
            examCount = 1;
        }
        else{
            examCount += 1;
        }
        Integer lastExamScore = exam.getTotalScore();
        Float averageScore = studentSubjectInformation.getAverageExamScore();
        if(averageScore == null){
            averageScore = Float.valueOf(0);
        }
        Float averageExamScore = (averageScore + lastExamScore)/examCount;
        studentSubjectInformationRepository.setStudentSubjectInformation(averageExamScore,
                lastExamScore, examCount, student.getId(), examsSubjectsType.getId());
    }

    private void updateStudentTypeTasks(User student, Exam exam, Integer[] scores){
        List<ExamsTypeTask> examsTypeTasks = examsTypeTaskRepository.findAllByExamsSubjectsType_Id(exam.getExamsSubjectsType().getId());
        for(ExamsTypeTask e : examsTypeTasks){
                StudentExamTypeTask studentExamTypeTask
                        = studentExamTypeTaskRepository.findFirstByStudent_IdAndExamsTypeTask_Id(student.getId(), e.getId());
                if(e.getMaxScore() == 1){
                    Integer total = studentExamTypeTask.getTotal() + 1;
                    Integer totalRight = studentExamTypeTask.getTotalRight() + scores[e.getTasksNumber() - 1];
                    studentExamTypeTaskRepository.setStudentExamTaskTotalRightAndTotal(totalRight, total, e.getId(), student.getId());
                }else {
                    Integer total = studentExamTypeTask.getTotal() + 1;
                    Float averageScore = studentExamTypeTask.getAverageScore();
                    if(averageScore == null){
                        averageScore = Float.valueOf(0);
                    }
                    Float newAverageScore = (averageScore + scores[e.getTasksNumber()-1])/total;
                    Integer score = scores[e.getTasksNumber() - 1];
                    studentExamTypeTaskRepository.setStudentExamTaskTotalAndScores(total,
                            score, newAverageScore, e.getId(), student.getId());
                }
        }

    }

    private String getUserName(String string){
        return string.substring(0, string.indexOf(" "));
    }

    private String getUserSurname(String string){
        return string.substring(string.indexOf(" ") + 1, string.length());
    }
}
