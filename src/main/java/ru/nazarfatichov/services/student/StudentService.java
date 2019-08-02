package ru.nazarfatichov.services.student;

import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.Exam;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.User;

import java.util.List;

public interface StudentService {
    List<User> getAllStudentsFromServer();

    List<StudentSubjectInformation> getStudentSubjectInformation(Long userId);

    void addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm);

    void updateStudentSubjectInformation(User student, ExamsSubjectsType examsSubjectsType, Exam exam);

    void updateStudentTypeTasks(User student, Exam exam, Integer[] scores);

}
