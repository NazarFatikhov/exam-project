package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarfatichov.models.Exam;

import java.util.Date;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE exam SET " +
            "student_id = ?, teacher_id = ?, type_id = ?, date = ?, total_score = ? WHERE id = ?;")
    void updateExam(Long studentId, Long teacherId, Long examsSubjectsTypeId, Date date, Integer totalScore, Long id);
}
