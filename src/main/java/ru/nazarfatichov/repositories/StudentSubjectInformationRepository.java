package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.nazarfatichov.models.ExamsSubjectsType;
import ru.nazarfatichov.models.StudentSubjectInformation;

import java.util.List;

public interface StudentSubjectInformationRepository extends JpaRepository<StudentSubjectInformation, Long> {
    List<StudentSubjectInformation> findAllByUserId(Long userId);
}
