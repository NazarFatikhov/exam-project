/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.services;

import java.util.List;

import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.Subject;

/**
 *
 * @author nazar
 */
public interface SubjectService {
    
    void addSubject(SubjectForm subjectForm);
    void addExamsSubjectsType(ExamsSubjectsTypesForm examsSubjectsTypesForm);

    List<Subject> getAllSubjects();
    List<ExamsSubjectsType> getAllExamsSubjectTypesFromServer();

}
