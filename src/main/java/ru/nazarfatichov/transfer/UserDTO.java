package ru.nazarfatichov.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long Id;
    private String name;
    private String surname;
}
