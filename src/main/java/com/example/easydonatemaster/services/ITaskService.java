package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.Urgency;
import com.example.easydonatemaster.entites.User;

import java.util.Date;
import java.util.List;

public interface ITaskService {
    Task addTask (Task task);
    Task updateTask(Task task);
    List<Task> getTasks();
    //@Modifying(clearAutomatically = true)
    Task getTaskById(int id);
    void deleteTask(int id);
    List<Task> getTaskByAssignedTo(Integer userId);
    List<Task> getTaskByFullName(String userName);
    List<Task> getTaskByStartingDate(Date date);
    int urgentTasksOfTheMonth(Urgency urg);
    int taskprogress (Progress progress);
    int taskStatByUser (String username);
    void deleteAutomatically ();

    User getAssignedTo(int id);
    Task taskByHelpDeman (int id);
    Task addByUser(int iduser , Task task);
    Task markAsDone( int id);
}
