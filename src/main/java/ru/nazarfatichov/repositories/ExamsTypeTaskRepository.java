package ru.nazarfatichov.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.nazarfatichov.models.ExamsTypeTask;

import java.util.List;

public interface ExamsTypeTaskRepository extends JpaRepository<ExamsTypeTask, Long> {
    ExamsTypeTask findFirstByTasksNumberAndExamsSubjectsType_Id(Integer tasksNumber, Long ExamsSubjectTypeId);

    List<ExamsTypeTask> findAllByExamsSubjectsType_Id(Long id);
}
