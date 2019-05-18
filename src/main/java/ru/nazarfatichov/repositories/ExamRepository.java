package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
