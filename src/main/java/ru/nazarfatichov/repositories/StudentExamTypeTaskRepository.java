package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarfatichov.models.StudentExamTypeTask;

import java.util.List;
import java.util.Optional;

public interface StudentExamTypeTaskRepository extends JpaRepository<StudentExamTypeTask, Long> {
    Optional<StudentExamTypeTask> findFirstByStudent_IdAndExamsTypeTask_Id(Long userId, Long examsTypeTaskId);

    List<Optional<StudentExamTypeTask>> findAllByStudent_IdAndExamsTypeTask_ExamsSubjectsType_Id(Long studentId, Long examsTypeTaskId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE student_exam_type_task SET " +
            "total_right = ?, total = ? WHERE exams_type_task_id = ? AND student_id = ?;")
    void setStudentExamTaskTotalRightAndTotal(Integer totalRight,
                                                 Integer total,
                                                 Long examsTypeTaskId,
                                                 Long userId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE student_exam_type_task SET " +
            "total = ?, last_score = ?, average_score = ? WHERE " +
            "exams_type_task_id = ? AND student_id = ?;")
    void setStudentExamTaskTotalAndScores(Integer total, Integer lastScore, Float averageScore, Long examsTypeTaskId,    Long userId);
}
