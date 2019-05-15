package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.StudentSubjectInformationRepository;
import ru.nazarfatichov.repositories.SubjectRepository;
import ru.nazarfatichov.repositories.UsersRepository;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Override
    public List<User> getAllStudentsFromServer() {
        return usersRepository.findAllByRole(Role.STUDENT);
    }

    @Override
    public List<StudentSubjectInformation> getStudentSubjectInformation(Long userId) {
        return studentSubjectInformationRepository.findAllByUserId(userId);
    }

    @Override
    public void addStudentSubjectInformation(StudentSubjectInformationForm studentSubjectInformationForm) {

        StudentSubjectInformation studentSubjectInformation = StudentSubjectInformation.builder()
                .user(usersRepository.findOne(studentSubjectInformationForm.getStudent()))
                .examsSubjectsType(examsSubjectsTypeRepository.findOne(studentSubjectInformationForm.getExamsSubjectsType()))
                .averageExamScore(studentSubjectInformationForm.getAverageExamScore())
                .averageTestScore(studentSubjectInformationForm.getAverageTestScore())
                .lastExamScore(studentSubjectInformationForm.getLastExamScore())
                .lastTestScore(studentSubjectInformationForm.getLastTestScore())
                .build();

        studentSubjectInformationRepository.save(studentSubjectInformation);

    }
}