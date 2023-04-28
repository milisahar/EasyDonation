package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.GiveAwayItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiveAwayItemRepositoy extends JpaRepository<GiveAwayItem , Integer> {
}
