package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepositoy extends JpaRepository<Event,Integer> {
}
