package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.Urgency;
import com.example.easydonatemaster.entites.User;
import com.example.easydonatemaster.repositories.CheckPointRepositoy;
import com.example.easydonatemaster.repositories.TaskRepositoy;
import com.example.easydonatemaster.repositories.UserRepositoy;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class TaskService implements ITaskService{

    TaskRepositoy taskRepositoy;
    CheckPointRepositoy checkPointRepositoy;
    UserRepositoy userRepositoy;
    @Override
    public Task addTask(Task task) {
        return taskRepositoy.save(task);
    }
    @Transactional
    @Override
    public Task updateTask(Task task) {

        return taskRepositoy.save(task);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepositoy.findAll();
    }

    @Override
    public Task getTaskById(int id) {
        return taskRepositoy.findById(id).orElse(null);
    }

    @Override
    public void deleteTask(int id) {
        taskRepositoy.deleteById(id);
    }

    @Override
    public List<Task> getTaskByAssignedTo(Integer userId) {
        List <Task> taskList = new ArrayList<>();
        for (Task t: getTasks()
        ) {
            if(t.getAssignedTo().getId() == userId)
                taskList.add(t);
        }
        return taskList;
    }

    @Override
    public List<Task> getTaskByFullName(String userName) {
        return taskRepositoy.findByAssignedToFirstName(userName);
    }


    @Override
    public List<Task> getTaskByStartingDate(Date date) {
        return taskRepositoy.findByStartingDate(date);
    }

    @Override
    public int urgentTasksOfTheMonth(Urgency urg) {
        return (taskRepositoy.retriveTasksByUrgency(urg)*100)/taskRepositoy.findAll().size();
    }

    @Override
    public int taskprogress(Progress progress) {
        return (taskRepositoy.retriveTasksByProgress(progress)*100)/taskRepositoy.findAll().size();
    }

    @Override
    public int taskStatByUser(String username) {
        return (taskRepositoy.retriveTasksByUserName(username)*100)/taskRepositoy.findAll().size();
    }

    @Override
    //@Scheduled(cron = "* * 23 * * *")
    public void deleteAutomatically() {

        Date date = Date.from(LocalDate.now().minusDays(5).atStartOfDay().toInstant(ZoneOffset.UTC));

        for (Task t:taskRepositoy.findAll()
        ) {
            if(t.getEndingingDate().compareTo(date)<0 && t.getProgress().equals(Progress.DONE))
                taskRepositoy.deleteById(t.getId());

        }



    }
    @PostConstruct
    public void  setUp() {
        Twilio.init("AC145f6e0c5245a6855cb4e4d42ebe95bd", "b5febbead47c99c19003df8228929d68");
    }

    //@Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "* */15 * * * *")
    public void affermations() {
        List<String> affirmations = new ArrayList<>();
        affirmations.add("\n \n I surrender my heart to the universe. \n EasyDonate appreciate ALL your EFFORTS, your TIME and ENERGY that you DONATE and wants to thank you by it's  way <3");
        affirmations.add("\n \n Everything I give to others is a gift to myself. As I give, I receive. \n EasyDonate appreciate ALL your EFFORTS, your TIME and ENERGY that you DONATE and wants to thank you by it's  way <3");
        affirmations.add("\n \n The best way to find yourself, is to lose yourself in the service of others. \n EasyDonate appreciate ALL your EFFORTS, your TIME and ENERGY that you DONATE and wants to thank you by it's  way <3");
        affirmations.add("\n \n Miracles are a natural part of living and giving. \n EasyDonate appreciate ALL your EFFORTS, your TIME and ENERGY that you DONATE and wants to thank you by it's  way <3");

        Message message = Message.creator(
                new PhoneNumber("+21652635303"),
                new PhoneNumber("+15677066534"),
                affirmations.get((int)(Math.random() * 3 + 0))).create();



    }

    @Override
    public User getAssignedTo(int id){
        if(getTaskById(id) != null)
            return this.getTaskById(id).getAssignedTo();
        else return null;
    }

    @Override
    public Task taskByHelpDeman(int id) {
        return taskRepositoy.findByHelpDemandsId(id);
    }

    @Override
    public Task addByUser(int iduser ,Task task) {
        User user = userRepositoy.findById(iduser).orElse(null);
        Task taskAdding = task;
        taskAdding.setAssignedTo(user);
        taskAdding.setProgress(Progress.STILL);
        return taskRepositoy.save(taskAdding);
    }

    @Override
    public Task markAsDone(int id) {
        Task newTask = taskRepositoy.getById(id);
        newTask.setProgress(Progress.DONE);
        return taskRepositoy.save(newTask);
    }

}