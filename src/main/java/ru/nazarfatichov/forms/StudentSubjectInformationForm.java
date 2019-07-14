package ru.nazarfatichov.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentSubjectInformationForm {
    private Long student;
    private Long examsSubjectsType;
    private Float averageExamScore;
    private Float averageTestScore;
    private Integer lastExamScore;
    private Integer lastTestScore;
 }
