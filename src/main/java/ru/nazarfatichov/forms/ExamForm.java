package ru.nazarfatichov.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class ExamForm {
    private Long studentId;
    private Long typeId;
    private String date;
    private Long teacherId;
    private Integer[] scores;
}
