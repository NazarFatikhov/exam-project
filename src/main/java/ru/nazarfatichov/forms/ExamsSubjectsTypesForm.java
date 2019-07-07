package ru.nazarfatichov.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamsSubjectsTypesForm {

    private String type;
    private String minScore;
    private String maxScore;
    private String taskCount;
    private String subject;
}
