package ru.nazarfatichov.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import ru.nazarfatichov.enums.Role;
import ru.nazarfatichov.forms.ExamTypeTaskForm;
import ru.nazarfatichov.models.ExamsTypeTask;
import ru.nazarfatichov.models.StudentExamTypeTask;
import ru.nazarfatichov.models.User;
import ru.nazarfatichov.repositories.ExamsSubjectsTypeRepository;
import ru.nazarfatichov.repositories.ExamsTypeTaskRepository;
import ru.nazarfatichov.repositories.StudentExamTypeTaskRepository;
import ru.nazarfatichov.repositories.UsersRepository;
import ru.nazarfatichov.services.ExamService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ExamTypeTaskController {

    @Autowired
    private ExamsSubjectsTypeRepository examsSubjectsTypeRepository;

    @Autowired
    private ExamsTypeTaskRepository examsTypeTaskRepository;

    @Autowired
    private ExamService examService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private StudentExamTypeTaskRepository studentExamTypeTaskRepository;

    @RequestMapping(path = "/admin/exam-type-task", method = RequestMethod.GET)
    public ModelAndView showExamTypeTaskPage(){
        ModelAndView modelAndView = new ModelAndView("exam-type-task");
        modelAndView.addObject("examsTypes", examsSubjectsTypeRepository.findAll());
        modelAndView.addObject("examsTypeTasks", examsTypeTaskRepository.findAll());
        return modelAndView;
    }

    @RequestMapping(path = "/admin/exam-type-task", method = RequestMethod.POST)
    public String addExamTypeTask(ExamTypeTaskForm examTypeTaskForm){
        ExamsTypeTask examsTypeTask = examService.addExamsTypeTask(examTypeTaskForm);
        for(User user : usersRepository.findAllByRole(Role.STUDENT)){
            StudentExamTypeTask studentExamTypeTask = StudentExamTypeTask.builder()
                    .total(0)
                    .totalRight(0)
                    .student(user)
                    .examsTypeTask(examsTypeTask)
                    .build();
            studentExamTypeTaskRepository.save(studentExamTypeTask);
        }
        return "redirect:" + MvcUriComponentsBuilder.fromMethodName(ExamTypeTaskController.class, "showExamTypeTaskPage").build();
    }

    @RequestMapping(path = "/teacher/subject-tasks", method = RequestMethod.POST)
    @ResponseBody
    public List<ExamsTypeTask> getExamsTypeTasksObject(@RequestParam(name = "subjectId") Long subjectId){
        List<ExamsTypeTask> examsTypeTaskList = examsTypeTaskRepository.findAllByExamsSubjectsType_Id(subjectId);
        examsTypeTaskList.forEach(s -> s.getExamsSubjectsType().setExamsTypeTasks(null));
        return examsTypeTaskList;
    }
}
