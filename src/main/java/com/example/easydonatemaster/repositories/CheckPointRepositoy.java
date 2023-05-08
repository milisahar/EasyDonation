package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.CheckPoint;
import com.example.easydonatemaster.entites.Progress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CheckPointRepositoy extends JpaRepository<CheckPoint , Integer> {
    List<CheckPoint> findByTaskId(Integer id);
    List<CheckPoint> findByTaskProgress(Progress pro);
    @Query("select count(c) from CheckPoint c order by c.dateDeCreation")
    List<Integer> getit();

    /* @Query("SELECT DATEPART('weekday', c.dateDeCreation), COUNT(c) FROM CheckPoint c GROUP BY DATEPART('weekday', c.dateDeCreation) ORDER BY DATEPART('weekday', c.dateDeCreation)")
     List<Object[]> countTasksByDayOfWeek();*/
    @Query("select c from CheckPoint c group by DAY (c.dateDeCreation)")
    List<CheckPoint> ff();
}
