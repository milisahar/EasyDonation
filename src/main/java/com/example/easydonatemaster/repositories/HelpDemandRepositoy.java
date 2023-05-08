package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.HelpDemand;
import com.example.easydonatemaster.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HelpDemandRepositoy extends JpaRepository<HelpDemand, Integer> {
    List<HelpDemand> findByRespondedTo(boolean state);

    User findByTaskAssignedTo(HelpDemand task);
    @Query("select hd from HelpDemand hd order by hd.dateDeCreation desc ")
    List<HelpDemand> orderd();
    @Query("select hd from HelpDemand hd order by hd.dateDeCreation asc ")
    List<HelpDemand> orderdDst();
}
