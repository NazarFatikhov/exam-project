package ru.nazarfatichov.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.enums.UserState;
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

    @OneToOne(targetEntity = UserInformation.class, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "user_information_id")
    private UserInformation userInformation;
    
    public static User from(UserForm form) {
        return User.builder()
                .emailAdress(form.getEmail())
                .hashPassword(form.getPassword())
                .build();
    }

    
}
