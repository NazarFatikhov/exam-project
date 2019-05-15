package ru.nazarfatichov.models;

import javax.persistence.*;

import lombok.*;
import org.hibernate.annotations.GeneratorType;
import ru.nazarfatichov.enums.Type;
import ru.nazarfatichov.forms.ExamsSubjectsTypesForm;
import ru.nazarfatichov.forms.UserForm;

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

    @Override
    public String toString() {
        return "(" + type.name() + ")" + " " + subject.getName();
    }
}
