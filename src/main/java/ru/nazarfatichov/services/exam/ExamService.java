package ru.nazarfatichov.services.exam;

import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.forms.TestForm;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.models.ExamsTypeTask;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.transfer.ExamDTO;

import java.text.ParseException;

public interface ExamService {

    Exam addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException;

    ExamsTypeTask addExamsTypeTask(ExamTypeTaskForm examTypeTaskForm);

    void saveExam(Exam exam) throws IncorrectSumOfTasksException;

    void saveExamTasks(Exam exam, Integer[] scores);

    void addTest(TestForm testForm, User teacher) throws ParseException, IncorrectSumOfTasksException;

    Exam addExam(ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException;
}
