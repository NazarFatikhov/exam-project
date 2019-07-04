package ru.nazarfatichov.forms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import ru.nazarfatichov.annotations.TasksConstraint;

@Data
public class ExamForm {
    @NotEmpty
    private String student;
    private Long typeId;
    @NotEmpty
    private String date;
    @NotEmpty
    private String teacher;
    @TasksConstraint
    private Integer[] scores;
}
