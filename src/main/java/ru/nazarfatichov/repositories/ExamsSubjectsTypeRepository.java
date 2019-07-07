/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.models.ExamsSubjectsType;

import java.util.Optional;

/**
 * @author nazar
 */
public interface ExamsSubjectsTypeRepository extends JpaRepository<ExamsSubjectsType, Long> {

    Optional<ExamsSubjectsType> findFirstByTypeAndAndSubject_Name(Type type, String subjectName);

}
