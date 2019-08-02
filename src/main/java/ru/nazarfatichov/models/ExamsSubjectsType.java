package ru.nazarfatichov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nazarfatichov.enums.Type;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author nazar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exams_subject_type", uniqueConstraints = {@UniqueConstraint(columnNames = {"type", "subject_id"})})
public class ExamsSubjectsType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated (value = EnumType.STRING)
    private Type type;
    
    @Column(name = "min_score")
    private Integer minScore;
    @Column(name = "max_score")
    private Integer maxScore;
    
    @Column(name = "tasks_count")
    private Integer tasksCount;
    
    @ManyToOne(targetEntity = Subject.class)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @OneToMany(mappedBy = "examsSubjectsType")
    private List<ExamsTypeTask> examsTypeTasks;

    @Override
    public String toString() {
        return "(" + type.name() + ")" + " " + subject.getName();
    }
}
