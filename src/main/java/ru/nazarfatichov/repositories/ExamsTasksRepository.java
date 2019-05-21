package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.ExamsTasks;

public interface ExamsTasksRepository extends JpaRepository<ExamsTasks, Long> {
}
