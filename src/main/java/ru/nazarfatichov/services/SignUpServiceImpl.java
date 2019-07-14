package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;
import ru.nazarfatichov.forms.SignUpForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;

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
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;
    
    @Override
    public void signUp(SignUpForm signUpForm) {
        
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());

        User user = User.builder()
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.STUDENT)
                .state(UserState.ACTIVE)
                .build();

        userRepository.save(user);

        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .user(user)
                .build();

        userInformationRepository.save(userInformation);


        initStudentSubjectInformation(user);
        initStudentExamTypeTasks(user);

    }

    @Override
    public void signUpteacher(SignUpForm signUpForm) {
        String hashPassword = passwordEncoder.encode(signUpForm.getPassword());

        User user = User.builder()
                .emailAdress(signUpForm.getEmail())
                .hashPassword(hashPassword)
                .role(Role.TEACHER)
                .state(UserState.ACTIVE)
                .build();

        userRepository.save(user);

        UserInformation userInformation = UserInformation.builder()
                .name(signUpForm.getName())
                .surname(signUpForm.getSurname())
                .user(user)
                .build();

        userInformationRepository.save(userInformation);

    }

    private void initStudentSubjectInformation(User student){
        List<ExamsSubjectsType> examsSubjectsTypes = examsSubjectsTypeRepository.findAll();
        for(ExamsSubjectsType e : examsSubjectsTypes) {
            StudentSubjectInformation studentSubjectInformation = StudentSubjectInformation.builder()
                    .user(student)
                    .examsSubjectsType(e)
                    .build();
            studentSubjectInformationRepository.save(studentSubjectInformation);
        }
    }

    private void initStudentExamTypeTasks(User student){
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
                studentExamTypeTaskRepository.save(studentExamTypeTask);
            }
        }
    }
    
}
