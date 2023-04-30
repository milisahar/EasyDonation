package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventType;
import com.example.easydonatemaster.repositories.EventRepositoy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Slf4j
@Service
@AllArgsConstructor
public class EventService implements IEventService{
private EventRepositoy eventRepositoy;



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
    public void archiveEvents() {
        List<Event> events = eventRepositoy.findByOngoing(false);
        for (Event event : events) {
            eventRepositoy.delete(event);
        }
    }
    @Scheduled(cron = "0 0 0 1/31 * * ")
    public void rappelEvents () {
        for (Event e : listEvent()
        ) {
            log.info("L'evenement: " +e.getTitle()+" a "+ e.getTicketList().stream().count()+ "participations! ");
        }
    }

    @Override
    public Long  NumberOfEvents( EventType eventType) {
        return eventRepositoy.findByEventType(eventType).stream().count();
    }

    @Override
    public List<Event> findByEventDate(Date eventDate) {
        return  eventRepositoy.findByEventDate(eventDate);
    }

    @Override
    public List<Event> findByPlace(String place) {
        return eventRepositoy.findByPlace(place);
    }
}
