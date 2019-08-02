/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.services.subject;

import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.forms.SubjectForm;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.Subject;

import java.util.List;

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
