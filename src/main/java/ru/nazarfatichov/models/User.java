package ru.nazarfatichov.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;

import javax.persistence.*;

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
public class User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "email_adress", nullable = false, unique = true)
    private String emailAdress;
    
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;
    
    @Enumerated(value = EnumType.STRING)
    private Role role;
    
    @Enumerated(value = EnumType.STRING)
    private UserState state;



}
