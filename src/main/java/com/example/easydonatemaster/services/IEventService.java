package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventType;

import java.util.Date;
import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    Event updateEvent(Event event, int id);
    List <Event> listEvent();
    void deleteEvent(int id);
    Event getEventById(int id);
    public void archiveEvents();
    public void rappelEvents ();
    Long  NumberOfEvents(EventType eventType );
    List<Event> findByEventDate( Date eventDate);
    List<Event> findByPlace( String place);


}
