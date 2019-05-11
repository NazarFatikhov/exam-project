package ru.nazarfatichov.forms;

import lombok.Data;

/**
 *
 * @author nazar
 */
@Data
public class SignUpForm {
   private String email;
   private String password;
   private String name;
   private String surname;
}
