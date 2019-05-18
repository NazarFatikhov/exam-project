package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.UserInformation;

import java.util.List;

@Data
@Builder
public class UserDTO {
    private List<StudentSubjectInformation> studentSubjectInformation;
    private String userName;
    private String userSurname;


}
