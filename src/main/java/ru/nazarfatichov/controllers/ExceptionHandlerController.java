package ru.nazarfatichov.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.exceptions.EntityNotFoundException;
import ru.nazarfatichov.exceptions.IncorrectSumOfTasksException;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ModelAndView getNotFoundExceptionPage(EntityNotFoundException ex){
        ModelAndView modelAndView = new ModelAndView("exception-page");
        modelAndView.addObject("message", ex.getEntity() + " not found");
        return modelAndView;
    }

    @ExceptionHandler(IncorrectSumOfTasksException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView getIncorrectSumExceptionPage(IncorrectSumOfTasksException ex){
        ModelAndView modelAndView = new ModelAndView("exception-page");
        modelAndView.addObject("message", "Sum of exam can't be bigger than maximum sum of this subject");
        return modelAndView;
    }


}
