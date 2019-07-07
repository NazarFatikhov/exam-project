package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestExamDto {

    private Long id;
    private RestUserDto student;
    private RestUserDto teacher;
    private RestExamSubjectTypeDto type;
    private String date;
    private Integer totalScore;
    private List<RestExamTaskDto> tasks;

}
