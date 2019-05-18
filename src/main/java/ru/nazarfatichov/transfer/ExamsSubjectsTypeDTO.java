package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.Subject;

import java.util.List;

@Builder
@Data
public class ExamsSubjectsTypeDTO {

    private List<Subject> subjects;
    private List<Type> types;
    private List<ExamsSubjectsType> examsSubjectsTypes;

}
