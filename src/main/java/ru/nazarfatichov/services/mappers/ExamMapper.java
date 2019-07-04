package ru.nazarfatichov.services.mappers;

import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.models.ExamsTasks;
import ru.nazarfatichov.models.UserInformation;
import ru.nazarfatichov.transfer.*;

import java.util.ArrayList;
import java.util.List;

public class ExamMapper {

    public RestExamDto getExamDto(Exam exam, UserInformation studentInformation,
                                  UserInformation teacherInformation, List<ExamsTasks> examsTasks){

        List<RestExamTaskDto> examTaskDtos = new ArrayList<>();
        for(ExamsTasks examsTask : examsTasks){
            RestExamTypeTaskDto restExamTypeTaskDto = RestExamTypeTaskDto.builder()
                    .taskNumber(examsTask.getExamsTypeTask().getTasksNumber())
                    .minScore(examsTask.getExamsTypeTask().getMinScore())
                    .maxScore(examsTask.getExamsTypeTask().getMaxScore())
                    .build();

            RestExamTaskDto restExamTaskDto = RestExamTaskDto.builder()
                    .typeTask(restExamTypeTaskDto)
                    .score(examsTask.getScore())
                    .build();

            examTaskDtos.add(restExamTaskDto);
        }

        RestUserDto teacherDto = RestUserDto.builder()
                .email(exam.getTeacher().getEmailAdress())
                .name(teacherInformation.getName())
                .surname(teacherInformation.getSurname())
                .build();

        RestUserDto studentDto = RestUserDto.builder()
                .email(exam.getStudent().getEmailAdress())
                .name(studentInformation.getName())
                .surname(studentInformation.getSurname())
                .build();

        RestExamSubjectTypeDto examSubjectTypeDto = RestExamSubjectTypeDto.builder()
                .subjectName(exam.getExamsSubjectsType().getSubject().getName())
                .typeName(exam.getExamsSubjectsType().getType().name())
                .maxScore(exam.getExamsSubjectsType().getMaxScore())
                .minScore(exam.getExamsSubjectsType().getMinScore())
                .taskCount(exam.getExamsSubjectsType().getTasksCount())
                .build();

        RestExamDto restExamDto = RestExamDto.builder()
                .date(exam.getDate())
                .student(studentDto)
                .teacher(teacherDto)
                .type(examSubjectTypeDto)
                .totalScore(exam.getTotalScore())
                .tasks(examTaskDtos)
                .build();

        return restExamDto;
    }

}
