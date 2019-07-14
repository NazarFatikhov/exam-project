/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.SubjectState;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.StudentSubjectInformation;
import ru.nazarfatichov.models.Subject;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.*;

import java.util.List;

/**
 *
 * @author nazar
 */
@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentSubjectInformationRepository studentSubjectInformationRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;
    
    @Override
    public void addSubject(SubjectForm subjectForm) {
        
        Subject subject = Subject.builder()
                .name(subjectForm.getName())
                .state(SubjectState.ACTIVE)
                .build();
        
        subjectRepository.save(subject);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void addExamsSubjectsType(ExamsSubjectsTypesForm examsSubjectsTypesForm) {
        Subject subject = subjectRepository.findOneByName(examsSubjectsTypesForm.getSubject()).get();

        ExamsSubjectsType examsSubjectsType = ExamsSubjectsType.builder()
                .maxScore(Integer.parseInt(examsSubjectsTypesForm.getMaxScore()))
                .minScore(Integer.parseInt(examsSubjectsTypesForm.getMinScore()))
                .subject(subject)
                .type(Type.valueOf(examsSubjectsTypesForm.getType()))
                .tasksCount(examsSubjectsTypesForm.getTaskCount().equals("") ? null : Integer.parseInt(examsSubjectsTypesForm.getTaskCount()))
                .build();

        List<User> students = usersRepository.findAllByRole(Role.STUDENT);

        examsSubjectsType = examsSubjectsTypeRepository.save(examsSubjectsType);

        for(User student : students) {
            StudentSubjectInformation studentSubjectInformation = StudentSubjectInformation.builder()
                    .examsSubjectsType(examsSubjectsType)
                    .user(student)
                    .examCount(0)
                    .testCount(0)
                    .build();
            studentSubjectInformationRepository.save(studentSubjectInformation);
        }
    }

    public List<ExamsSubjectsType> getAllExamsSubjectTypesFromServer(){
        return examsSubjectsTypeRepository.findAll();
    }


}
