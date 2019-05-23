package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.StudentExamTypeTask;

public interface StudentExamTypeTaskRepository extends JpaRepository<StudentExamTypeTask, Long> {
}
