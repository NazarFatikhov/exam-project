package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.models.ExamsTypeTask;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.ExamsTypeTaskRepository;
import ru.nazarfatichov.services.ExamService;

import javax.jws.WebParam;

@Controller
public class ExamTypeTaskController {

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private ExamService examService;

    @RequestMapping(path = "/admin/exam-type-task", method = RequestMethod.GET)
    public ModelAndView showExamTypeTaskPage(){
        ModelAndView modelAndView = new ModelAndView("exam-type-task");
        modelAndView.addObject("examsTypes", examsSubjectsTypeRepository.findAll());
        modelAndView.addObject("examsTypeTasks", examsTypeTaskRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/admin/exam-type-task", method = RequestMethod.POST)
    public String addExamTypeTask(ExamTypeTaskForm examTypeTaskForm){
        examService.addExamsTypeTask(examTypeTaskForm);
        return "redirect:/admin/exam-type-task";
    }
}
