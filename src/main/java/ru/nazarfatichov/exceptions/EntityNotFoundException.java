package ru.nazarfatichov.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
public class EntityNotFoundException extends RuntimeException {

    public String entity;

    public EntityNotFoundException(String entity) {
        super();
        this.entity = entity;
    }

    public EntityNotFoundException(){
        super();
    }


}
