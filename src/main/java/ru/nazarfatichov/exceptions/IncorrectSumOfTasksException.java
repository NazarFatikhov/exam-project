package ru.nazarfatichov.exceptions;

public class IncorrectSumOfTasksException extends Exception {
    @Override
    public String getMessage() {
        return "Incorrect sum of tasks";
    }
}
