package ru.nazarfatichov.services;

import com.sun.javafx.collections.IntegerArraySyncer;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;

import javax.transaction.Transactional;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
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

    @Override
    public void addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException {
        User student = usersRepository.findOne(examForm.getStudentId());
        User teacher = usersRepository.findOne(examForm.getTeacherId());
        ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examForm.getTypeId());

        SimpleDateFormat simpleFormatter = new SimpleDateFormat("YYYY-mm-dd");
        Date date = simpleFormatter.parse(examForm.getDate());

        Integer[] scores = examForm.getScores();

        Integer totalScore = Arrays.stream(scores).mapToInt(Integer::intValue).sum();


        Exam exam = Exam.builder()
                .student(student)
                .teacher(teacher)
                .date(date)
                .examsSubjectsType(examsSubjectsType)
                .totalScore(totalScore)
                .build();

        if(exam.getTotalScore() > examsSubjectsType.getMaxScore() || exam.getTotalScore() < examsSubjectsType.getMinScore()){
            throw new IncorrectSumOfTasksException();
        }

        examRepository.save(exam);

        ExamsTasks examsTasks = null;
        for (int i = 0; i < scores.length; i++) {
            examsTasks = ExamsTasks.builder()
                    .exam(exam)
                    .examsTypeTask(examsTypeTaskRepository.findFirstByTasksNumberAndExamsSubjectsType_Id(i + 1, examsSubjectsType.getId()))
                    .score(scores[i])
                    .build();

            examsTasksRepository.save(examsTasks);
        }

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
}
