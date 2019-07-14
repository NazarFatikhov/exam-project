package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.forms.StudentSubjectInformationForm;
import ru.nazarfatichov.models.*;
import ru.nazarfatichov.repositories.*;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

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
                .user(usersRepository.findById(studentSubjectInformationForm.getStudent()).get())
                .examsSubjectsType(examsSubjectsTypeRepository.findById(studentSubjectInformationForm.getExamsSubjectsType()).get())
                .averageExamScore(studentSubjectInformationForm.getAverageExamScore())
                .averageTestScore(studentSubjectInformationForm.getAverageTestScore())
                .lastExamScore(studentSubjectInformationForm.getLastExamScore())
                .lastTestScore(studentSubjectInformationForm.getLastTestScore())
                .build();

        studentSubjectInformationRepository.save(studentSubjectInformation);

    }

    @Override
    public void updateStudentSubjectInformation(User student, ExamsSubjectsType examsSubjectsType, Exam exam) {
        Optional<StudentSubjectInformation> studentSubjectInformationCandidate =
                studentSubjectInformationRepository.findFirstByUser_IdAndExamsSubjectsType_Id(student.getId(), examsSubjectsType.getId());
        Integer examCount = studentSubjectInformationCandidate.get().getExamCount();
        if(examCount == null){
            examCount = 1;
        }
        else{
            examCount += 1;
        }
        Integer lastExamScore = exam.getTotalScore();
        Float averageScore = studentSubjectInformationCandidate.get().getAverageExamScore();
        if(averageScore == null){
            averageScore = Float.valueOf(0);
        }
        Float averageExamScore = (averageScore + lastExamScore)/examCount;
        studentSubjectInformationRepository.setStudentSubjectInformation(averageExamScore,
                lastExamScore, examCount, student.getId(), examsSubjectsType.getId());
    }

    @Override
    public void updateStudentTypeTasks(User student, Exam exam, Integer[] scores) {
        List<ExamsTypeTask> examsTypeTasksCandidates = examsTypeTaskRepository.findAllByExamsSubjectsType_Id(exam.getExamsSubjectsType().getId());
        for(ExamsTypeTask ex : examsTypeTasksCandidates){
            Optional<StudentExamTypeTask> studentExamTypeTaskCandidate
                    = studentExamTypeTaskRepository.findFirstByStudent_IdAndExamsTypeTask_Id(student.getId(), ex.getId());
            if(ex.getMaxScore() == 1){
                Integer total = studentExamTypeTaskCandidate.get().getTotal() + 1;
                Integer totalRight = studentExamTypeTaskCandidate.get().getTotalRight() + scores[ex.getTasksNumber() - 1];
                studentExamTypeTaskRepository.setStudentExamTaskTotalRightAndTotal(totalRight, total, ex.getId(), student.getId());
            }else {
                Integer total = studentExamTypeTaskCandidate.get().getTotal() + 1;
                Float averageScore = studentExamTypeTaskCandidate.get().getAverageScore();
                if(averageScore == null){
                    averageScore = Float.valueOf(0);
                }
                Float newAverageScore = (averageScore + scores[ex.getTasksNumber()-1])/total;
                Integer score = scores[ex.getTasksNumber() - 1];
                studentExamTypeTaskRepository.setStudentExamTaskTotalAndScores(total,
                        score, newAverageScore, ex.getId(), student.getId());
            }
        }
    }

}
