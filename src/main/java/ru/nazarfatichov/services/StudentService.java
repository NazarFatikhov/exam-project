package ru.nazarfatichov.services;

import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.transfer.UserDTO;

import java.util.List;

public interface StudentService {
    List<User> getAllStudentsFromServer();

    List<StudentSubjectInformation> getStudentSubjectInformation(Long userId);

    void addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm);

    void updateStudentSubjectInformation(User student, ExamsSubjectsType examsSubjectsType, Exam exam);

    void updateStudentTypeTasks(User student, Exam exam, Integer[] scores);

}
