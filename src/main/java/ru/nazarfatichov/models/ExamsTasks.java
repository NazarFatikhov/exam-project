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
@Table(name = "exams_tasks", uniqueConstraints = {@UniqueConstraint(columnNames = {"exam_id", "exam_type_task_id"})})
public class ExamsTasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @ManyToOne
    @JoinColumn(name = "exam_type_task_id", nullable = false)
    private ExamsTypeTask examsTypeTask;

    @Column(nullable = false)
    private Integer score;

}
