/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.Subject;

import java.util.Optional;

/**
 *
 * @author nazar
 */
public interface SubjectRepository extends JpaRepository<Subject, Long>{
    Optional<Subject> findOneByName(String name);
}
