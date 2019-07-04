package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestExamSubjectTypeDto {

    private String typeName;
    private String subjectName;
    private Integer minScore;
    private Integer maxScore;
    private Integer taskCount;

}
