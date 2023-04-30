package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.repositories.EventRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements IEventService{
private EventRepositoy eventRepositoy;

    public EventService(EventRepositoy eventRepositoy) {
        this.eventRepositoy = eventRepositoy;
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepositoy.save(event) ;
    }

    @Override
    public Event updateEvent(Event event) {
        return eventRepositoy.save(event);
    }

    @Override
    public List<Event> listEvent() {
        return eventRepositoy.findAll();
    }

    @Override
    public void deleteEvent(int id) {
        eventRepositoy.deleteById(id);

    }
}
