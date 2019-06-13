package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;
import ru.nazarfatichov.transfer.UserDTO;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private UserInformationRepository userInformationRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;

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

    @Override
    public void updateStudentSubjectInformation(User student, ExamsSubjectsType examsSubjectsType, Exam exam) {
        StudentSubjectInformation studentSubjectInformation =
                studentSubjectInformationRepository.findFirstByUser_IdAndExamsSubjectsType_Id(student.getId(), examsSubjectsType.getId());
        Integer examCount = studentSubjectInformation.getExamCount();
        if(examCount == null){
            examCount = 1;
        }
        else{
            examCount += 1;
        }
        Integer lastExamScore = exam.getTotalScore();
        Float averageScore = studentSubjectInformation.getAverageExamScore();
        if(averageScore == null){
            averageScore = Float.valueOf(0);
        }
        Float averageExamScore = (averageScore + lastExamScore)/examCount;
        studentSubjectInformationRepository.setStudentSubjectInformation(averageExamScore,
                lastExamScore, examCount, student.getId(), examsSubjectsType.getId());
    }

    @Override
    public void updateStudentTypeTasks(User student, Exam exam, Integer[] scores) {
        List<ExamsTypeTask> examsTypeTasks = examsTypeTaskRepository.findAllByExamsSubjectsType_Id(exam.getExamsSubjectsType().getId());
        for(ExamsTypeTask e : examsTypeTasks){
            StudentExamTypeTask studentExamTypeTask
                    = studentExamTypeTaskRepository.findFirstByStudent_IdAndExamsTypeTask_Id(student.getId(), e.getId());
            if(e.getMaxScore() == 1){
                Integer total = studentExamTypeTask.getTotal() + 1;
                Integer totalRight = studentExamTypeTask.getTotalRight() + scores[e.getTasksNumber() - 1];
                studentExamTypeTaskRepository.setStudentExamTaskTotalRightAndTotal(totalRight, total, e.getId(), student.getId());
            }else {
                Integer total = studentExamTypeTask.getTotal() + 1;
                Float averageScore = studentExamTypeTask.getAverageScore();
                if(averageScore == null){
                    averageScore = Float.valueOf(0);
                }
                Float newAverageScore = (averageScore + scores[e.getTasksNumber()-1])/total;
                Integer score = scores[e.getTasksNumber() - 1];
                studentExamTypeTaskRepository.setStudentExamTaskTotalAndScores(total,
                        score, newAverageScore, e.getId(), student.getId());
            }
        }
    }

}
