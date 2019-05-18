package ru.nazarfatichov.services;

import com.sun.javafx.collections.IntegerArraySyncer;
import com.sun.org.apache.xalan.internal.xsltc.util.IntegerArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.ExamRepository;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.UsersRepository;

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
    ExamRepository examRepository;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Override
    public void addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException {
            User student = usersRepository.findOne(examForm.getStudentId());
            User teacher = usersRepository.findOne(examForm.getTeacherId());
            ExamsSubjectsType examsSubjectsType = examsSubjectsTypeRepository.findOne(examForm.getTypeId());

            SimpleDateFormat simpleFormatter = new SimpleDateFormat("YYYY-mm-dd");
            Date date = simpleFormatter.parse(examForm.getDate());

            Integer totalScore = Arrays.stream(examForm.getScores()).mapToInt(Integer::intValue).sum();

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
    }
}
