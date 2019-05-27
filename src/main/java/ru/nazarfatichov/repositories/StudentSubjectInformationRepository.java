package ru.nazarfatichov.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nazarfatichov.models.StudentSubjectInformation;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface StudentSubjectInformationRepository extends JpaRepository<StudentSubjectInformation, Long> {
    List<StudentSubjectInformation> findAllByUserId(Long userId);

    StudentSubjectInformation findFirstByUser_IdAndExamsSubjectsType_Id(Long userId, Long typeId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE student_subject_information SET " +
            "average_exam_score = ?, last_exam_score = ?, exam_count = ? " +
            "WHERE user_id = ? AND exams_subject_type_id = ?;")
    void setStudentSubjectInformation(Float averageExamScore,
                                      Integer lastExamScore,
                                      Integer examCount,
                                      Long userId,
                                      Long typeId);


}
