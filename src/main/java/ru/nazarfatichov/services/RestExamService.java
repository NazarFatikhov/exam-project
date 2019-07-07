package ru.nazarfatichov.services;

import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.transfer.RestExamDto;

import java.text.ParseException;
import java.util.List;

public interface RestExamService {

    List<RestExamDto> getAllExamDtos();

    RestExamDto getExamDtoById(Long id);

    RestExamDto updateExam(Long id, RestExamDto examDTO) throws ParseException;

    Exam addNewExam(RestExamDto examDTO) throws IncorrectSumOfTasksException, ParseException;

    void deleteExam(Long id);

}
