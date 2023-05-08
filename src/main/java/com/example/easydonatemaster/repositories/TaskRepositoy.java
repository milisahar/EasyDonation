package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Progress;
import com.example.easydonatemaster.entites.Task;
import com.example.easydonatemaster.entites.Urgency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface TaskRepositoy extends JpaRepository<Task, Integer> {

    @Query("select count (t)from Task t where t.urgency =:state")
    Integer retriveTasksByUrgency (@Param("state") Urgency state);

    @Query("select count (t)from Task t where t.progress =:state")
    Integer retriveTasksByProgress (@Param("state") Progress state);
    @Query("select count (t)from Task t where t.assignedTo.firstName =:name")
    Integer retriveTasksByUserName (@Param("name") String name);

    //List<Task> findByAssignedToId(Integer assignedToId);

    //List<Task> findByAssignedToId(Integer id);
    List<Task> findByStartingDate (Date date);
    List<Task> findByAssignedToFirstName(String name);
    @Query("select t from Task t where t.startingDate =:start and t.endingingDate=:endD ")
    List<Task> retrieveTasksByDates (@Param("start") LocalDate startDate, @Param("endD") LocalDate endDate);
    Task findByHelpDemandsId(Integer d);
}
