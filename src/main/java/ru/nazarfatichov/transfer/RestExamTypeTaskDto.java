package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestExamTypeTaskDto {

    private Integer taskNumber;
    private Integer minScore;
    private Integer maxScore;

}
