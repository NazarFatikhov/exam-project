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
@Table(name = "student_exam_type_task",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"student_id", "exams_type_task_id"})})
public class StudentExamTypeTask {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(nullable = false)
        private User student;

        @ManyToOne(targetEntity = ExamsTypeTask.class)
        @JoinColumn(name = "exams_type_task_id", nullable = false)
        private ExamsTypeTask examsTypeTask;

        private Integer total;

        @Column(name = "total_right")
        private Integer totalRight;

        @Column(name = "average_score")
        private Float averageScore;

        @Column(name = "last_score")
        private Integer lastScore;


}
