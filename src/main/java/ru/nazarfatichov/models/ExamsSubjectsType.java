package ru.nazarfatichov.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratorType;
import ru.nazarfatichov.enums.Type;

/**
 *
 * @author nazar
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exams_subject_type")
public class ExamsSubjectsType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    @Enumerated (value = EnumType.STRING)
    private Type type;
    
    @Column(name = "min_score")
    private Integer minScore;
    @Column(name = "max_score")
    private Integer maxScore;
    
    @Column(name = "tasks_count")
    private Integer tasksCount;
    
    @ManyToOne(targetEntity = Subject.class)
    @JoinColumn(name = "subject_id", unique = true)
    private Subject subject;
    
}
