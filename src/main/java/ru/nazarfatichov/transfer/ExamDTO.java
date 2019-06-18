package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {

    private Long teacherId;
    private Long studentId;
    private Long typeId;
    private Integer[] scores;
    private String date;

}
