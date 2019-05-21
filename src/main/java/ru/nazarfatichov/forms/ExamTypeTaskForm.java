package ru.nazarfatichov.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExamTypeTaskForm {
    private Long typeId;
    private Integer taskNumber;
    private Integer minScore;
    private Integer maxScore;
}
