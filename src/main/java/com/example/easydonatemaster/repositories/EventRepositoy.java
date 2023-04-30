package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventType;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public interface EventRepositoy extends JpaRepository<Event,Integer> {
    List<Event> findAllByEventType(EventType eventType); // liste des ev de mm type
    List<Event> findByOngoing(boolean ongoing); // pour archiver
    List<Event> findByEventType(EventType eventType);
    List<Event> findByEventDate( Date eventDate);
    List<Event> findByPlace( String place);

}
