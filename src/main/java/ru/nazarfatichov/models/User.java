package ru.nazarfatichov.models;

import lombok.*;
import org.hibernate.annotations.Fetch;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;

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
@Table(name = "users")
@ToString(exclude = {"examList", "studentSubjectInformationList", "studentExamTypeTaskList"})
public class User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email_address", nullable = false, unique = true)
    private String emailAdress;
    
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;
    
    @Enumerated(value = EnumType.STRING)
    private Role role;
    
    @Enumerated(value = EnumType.STRING)
    private UserState state;

    @OneToOne
    @JoinColumn(name = "user_information_id", nullable = false)
    private UserInformation userInformation;

    @OneToMany(mappedBy = "student")
    private List<Exam> examList;

    @OneToMany(mappedBy = "user")
    private List<StudentSubjectInformation> studentSubjectInformationList;

    @OneToMany(mappedBy = "student")
    private List<StudentExamTypeTask> studentExamTypeTaskList;



}
