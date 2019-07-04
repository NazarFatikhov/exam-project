package ru.nazarfatichov.services;

import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.transfer.ExamDTO;
import ru.nazarfatichov.transfer.RestExamDto;

import java.text.ParseException;
import java.util.List;

public interface RestExamService {

    List<RestExamDto> getAllExamDtos();

    RestExamDto getExamDtoById(Long id);

    Exam updateExam(Long id, ExamDTO examDTO) throws ParseException;

    Exam addNewExam(ExamDTO examDTO) throws IncorrectSumOfTasksException, ParseException;

    void deleteExam(Long id);

}
