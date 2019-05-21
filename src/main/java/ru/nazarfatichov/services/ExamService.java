package ru.nazarfatichov.services;

import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.forms.ExamForm;
import ru.nazarfatichov.forms.ExamTypeTaskForm;

import java.text.ParseException;

public interface ExamService {

    void addExam(ExamForm examForm) throws IncorrectSumOfTasksException, ParseException;

    void addExamsTypeTask(ExamTypeTaskForm examTypeTaskForm);
}
