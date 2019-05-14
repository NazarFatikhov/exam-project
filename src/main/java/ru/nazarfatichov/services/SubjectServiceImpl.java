/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.SubjectState;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.Subject;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.SubjectRepository;

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
        Subject subject = subjectRepository.findOneByName(examsSubjectsTypesForm.getSubject());

        ExamsSubjectsType examsSubjectsType = ExamsSubjectsType.builder()
                .maxScore(Integer.parseInt(examsSubjectsTypesForm.getMaxScore()))
                .minScore(Integer.parseInt(examsSubjectsTypesForm.getMinScore()))
                .subject(subject)
                .type(Type.valueOf(examsSubjectsTypesForm.getType()))
                .tasksCount(Integer.parseInt(examsSubjectsTypesForm.getTaskCount()))
                .build();

        examsSubjectsTypeRepository.save(examsSubjectsType);
    }

    public List<ExamsSubjectsType> getAllExamsSubjectTypesFromServer(){
        return examsSubjectsTypeRepository.findAll();
    }


}
