package ru.nazarfatichov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nazarfatichov.forms.ExamForm;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "exam")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @ManyToOne(targetEntity = ExamsSubjectsType.class)
    @JoinColumn(name = "type_id", nullable = false)
    private ExamsSubjectsType examsSubjectsType;

    @Column(nullable = false)
    private Date date;

    @Column(name = "total_score", nullable = false)
    private Integer totalScore;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;

}
