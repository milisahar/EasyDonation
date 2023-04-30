package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;

import java.util.List;

public interface IEventService {
    Event addEvent(Event event);
    Event updateEvent(Event event);
    List <Event> listEvent();
    void deleteEvent(int id);


}
