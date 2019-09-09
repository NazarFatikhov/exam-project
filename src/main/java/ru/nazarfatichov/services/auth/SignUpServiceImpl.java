package ru.nazarfatichov.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;
import ru.nazarfatichov.forms.SignUpForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nazar
 */
@Service
public class SignUpServiceImpl implements SignUpService{
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;


    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;

    @Override
    public void signUp(SignUpForm signUpForm) {
        
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());

        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .build();

        User user = User.builder()
                .userInformation(userInformation)
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.STUDENT)
                .state(UserState.ACTIVE)
                .build();

        userRepository.save(user);

        studentSubjectInformationRepository.saveAll(createStudentSubjectInformationList(user));

        studentExamTypeTaskRepository.saveAll(createStudentExamTypeTaskList(user));

    }

    @Override
    public void signUpTeacher(SignUpForm signUpForm) {
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());

        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .build();

        User user = User.builder()
                .userInformation(userInformation)
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.TEACHER)
                .state(UserState.ACTIVE)
                .build();

        userRepository.save(user);


    }

    private List<StudentSubjectInformation> createStudentSubjectInformationList(User student){
        List<StudentSubjectInformation> studentSubjectInformationList = new ArrayList<>();

        List<ExamsSubjectsType> examsSubjectsTypes = examsSubjectsTypeRepository.findAll();
        examsSubjectsTypes.forEach(t -> studentSubjectInformationList.add(
                StudentSubjectInformation.builder()
                        .user(student)
                        .examsSubjectsType(t)
                        .examCount(0)
                        .testCount(0)
                        .build()
                )
        );

        return studentSubjectInformationList;
    }

    private List<StudentExamTypeTask> createStudentExamTypeTaskList(User student){
        List<StudentExamTypeTask> studentExamTypeTaskList = new ArrayList<>();
        List<ExamsSubjectsType> examsSubjectsTypes = examsSubjectsTypeRepository.findAll();
        for(ExamsSubjectsType type : examsSubjectsTypes){
            List<ExamsTypeTask> examsTypeTasks = examsTypeTaskRepository.findAllByExamsSubjectsType_Id(type.getId());
            for(ExamsTypeTask task : examsTypeTasks){
                StudentExamTypeTask studentExamTypeTask = StudentExamTypeTask.builder()
                        .student(student)
                        .examsTypeTask(task)
                        .totalRight(0)
                        .total(0)
                        .build();
                studentExamTypeTaskList.add(studentExamTypeTask);
            }
        }
        return studentExamTypeTaskList;
    }
    
}
