package ru.nazarfatichov.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.access.method.P;
import ru.nazarfatichov.annotations.TasksConstraint;

import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
