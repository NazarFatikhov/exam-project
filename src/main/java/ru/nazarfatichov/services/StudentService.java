package ru.nazarfatichov.services;

import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.models.UserInformation;
import ru.nazarfatichov.transfer.UserDTO;

import java.util.List;

public interface StudentService {
    List<User> getAllStudentsFromServer();

    List<StudentSubjectInformation> getStudentSubjectInformation(Long userId);

    void addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm);

}
