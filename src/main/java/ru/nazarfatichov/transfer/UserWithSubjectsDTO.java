package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;
import ru.nazarfatichov.models.StudentExamTypeTask;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.UserInformation;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class UserWithSubjectsDTO {
    private List<StudentSubjectInformation> studentSubjectInformation;
    private String userName;
    private String userSurname;


}
