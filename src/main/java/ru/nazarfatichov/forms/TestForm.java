package ru.nazarfatichov.forms;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class TestForm {

    @NotEmpty
    private String student;
    private Long typeId;
    @NotEmpty
    private String date;
    private Integer score;
}
