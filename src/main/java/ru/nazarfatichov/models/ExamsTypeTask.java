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
@Table(name = "exams_type_task", uniqueConstraints = {@UniqueConstraint(columnNames = {"exams_subjects_type_id", "task_number"})})
public class ExamsTypeTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "task_number")
    private Integer tasksNumber;

    @Column(nullable = false, name = "min_score")
    private Integer minScore;

    @Column(nullable = false, name = "max_score")
    private Integer maxScore;

    @ManyToOne
    @JoinColumn(name = "exams_subjects_type_id")
    private ExamsSubjectsType examsSubjectsType;

}
