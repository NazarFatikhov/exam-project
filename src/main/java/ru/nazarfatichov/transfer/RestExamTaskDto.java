package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestExamTaskDto {

    private RestExamTypeTaskDto typeTask;
    private Integer score;

}
