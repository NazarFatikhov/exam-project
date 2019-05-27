package ru.nazarfatichov.transfer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private Long Id;
    private String name;
    private String surname;
}
