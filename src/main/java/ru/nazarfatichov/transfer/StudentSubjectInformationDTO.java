package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.User;

import java.util.List;

@Builder
@Data
public class StudentSubjectInformationDTO {
    private List<ExamsSubjectsType> examsSubjectsTypes;
    private List<User> students;
}
