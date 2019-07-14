package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;
import ru.nazarfatichov.models.StudentSubjectInformation;

import java.util.List;

@Data
@Builder
public class UserWithSubjectsDTO {
    private List<StudentSubjectInformation> studentSubjectInformation;
    private String userName;
    private String userSurname;


}
