package ru.nazarfatichov.validators;

import ru.nazarfatichov.annotations.TasksConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TaskScoreValidator implements ConstraintValidator<TasksConstraint, Integer[]> {
    @Override
    public void initialize(TasksConstraint constraintAnnotation) { }

    @Override
    public boolean isValid(Integer[] tasks, ConstraintValidatorContext constraintValidatorContext) {
        for(int i = 0; i < tasks.length; i++){
            if(tasks[i] < 0){
                return false;
            }
        }
        return true;
    }
}
