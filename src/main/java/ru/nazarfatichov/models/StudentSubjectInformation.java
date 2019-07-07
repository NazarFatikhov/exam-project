package ru.nazarfatichov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "student_subject_information", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "exams_subject_type_id"})})
public class StudentSubjectInformation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = ExamsSubjectsType.class)
    @JoinColumn(name = "exams_subject_type_id")
    private ExamsSubjectsType examsSubjectsType;

    @Column(name = "average_exam_score")
    private Float averageExamScore;
    @Column(name = "average_test_score")
    private Float averageTestScore;
    @Column(name = "last_exam_score")
    private Integer lastExamScore;
    @Column(name = "last_test_score")
    private Integer lastTestScore;
    @Column(name = "exam_count")
    private Integer examCount;
    @Column(name = "test_count")
    private Integer testCount;

}
