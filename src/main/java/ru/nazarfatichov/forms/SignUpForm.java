package ru.nazarfatichov.forms;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 *
 * @author nazar
 */
@Data
public class SignUpForm {
   @Email
   private String email;
   @Size(min = 3, message = "password should be more than 3 characters")
   private String password;
   @NotEmpty
   private String name;
   @NotEmpty
   private String surname;
}
