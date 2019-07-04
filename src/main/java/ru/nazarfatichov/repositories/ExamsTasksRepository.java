package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.ExamsTasks;

import java.util.List;

public interface ExamsTasksRepository extends JpaRepository<ExamsTasks, Long> {

    List<ExamsTasks> findAllByExam_Id(Long examId);

}
