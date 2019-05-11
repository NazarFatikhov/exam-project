/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nazarfatichov.enums.SubjectState;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.models.Subject;
import ru.nazarfatichov.repositories.SubjectRepository;

/**
 *
 * @author nazar
 */
@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    SubjectRepository subjectRepository;
    
    @Override
    public void addSubject(SubjectForm subjectForm) {
        
        Subject subject = Subject.builder()
                .name(subjectForm.getName())
                .state(SubjectState.ACTIVE)
                .build();
        
        subjectRepository.save(subject);
    }
    
}
