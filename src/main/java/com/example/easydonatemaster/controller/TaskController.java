package com.example.easydonatemaster.controller;


import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.Urgency;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.services.ITaskService;
import com.example.easydonatemaster.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    ITaskService iTaskService;
    TaskService taskService;
    @PostMapping("/add")
    Task addTask (@RequestBody Task task) {
        return iTaskService.addTask(task);
    }


    @PostMapping("/add/user/{id}")
    Task addTaskByUser (@PathVariable("id") int id , @RequestBody Task task) {


        return iTaskService.addByUser(id,task);
    }

    @PutMapping("/update/{id}")
    Task updateTask (@PathVariable("id") int id ,@RequestBody Task taskk) {
        Task updateResponse = iTaskService.updateTask(taskk);
        return updateResponse;
    }

    @PutMapping("/done/{id}")
    Task markAsDone (@PathVariable("id") int id) {
        Task updateResponse = iTaskService.markAsDone(id);
        return updateResponse;
    }

    @GetMapping()
    List<Task> getTasks(){
        return iTaskService.getTasks();
    }
    @GetMapping("/{id}")
    Task getTask(@PathVariable int id){
        return iTaskService.getTaskById(id);
    }

    @DeleteMapping("/delete/{id}")
    void deleteTask(@PathVariable int id){
        iTaskService.deleteTask(id);
    }


    @GetMapping("/byUser/{id}")
    List<Task> getTasksByAssignedTo(@PathVariable("id")int id){
        return iTaskService.getTaskByAssignedTo(id);
    }

    @GetMapping("/by_starting_date/{date}")
    List<Task> getTasksByStartingDate(@PathVariable Date date){
      /*  LocalDate dtt = LocalDate.parse(date, DateTimeFormatter.BASIC_ISO_DATE);
        Date dt =java.util.Date.from(dtt.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());

       */
        return iTaskService.getTaskByStartingDate(date);
    }


    @GetMapping("/urgency/statistics/{urg}")
    String tasksOfTheMonth(@PathVariable Urgency urg){
        return iTaskService.urgentTasksOfTheMonth(urg)+"%";
    }
    @GetMapping("/progress/statisctics/{progress}")
    String tasksProgressPercent(@PathVariable Progress progress){
        return iTaskService.taskprogress(progress)+"%";
    }

    @GetMapping("/by_username/{name}")
    List<Task> tasksByUserName(@PathVariable String name){
        return iTaskService.getTaskByFullName(name);
    }
    @GetMapping("/user/statistics/{name}")
    String tasksByUserNameStat(@PathVariable String name){
        return iTaskService.taskStatByUser(name)+" %";
    }
    @DeleteMapping("/time")
    List<Task> time(){
        iTaskService.deleteAutomatically();
        return iTaskService.getTasks();
    }

    @GetMapping("/getAssignedTo/{id}")
    User getAssignedTo(@PathVariable("id") int id){
        return iTaskService.getAssignedTo(id);
    }
    @GetMapping("/helpDemand/{id}")
    Task getByHelpDemand(@PathVariable("id") int id){
        return iTaskService.taskByHelpDeman(id);
    }


}