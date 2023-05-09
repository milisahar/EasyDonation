package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventType;
import com.example.easydonatemaster.services.IEventService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@AllArgsConstructor
@RestController
@CrossOrigin(origins="*")
public class EventController {
    IEventService iEventService;

    @PostMapping("/event/add")
    Event addEvent(@RequestBody Event event) {
        return iEventService.addEvent(event);
    }

    @GetMapping("/event/get/{id}")
    Event getEventById( @PathVariable int id){
        return  iEventService.getEventById(id);
    }
    @PostMapping("/event/update/{id}")
    Event updateEvent(@RequestBody Event event ,@PathVariable int id) {
        return iEventService.updateEvent(event,id);
    }

    @GetMapping("/event/listEvent")
    public List<Event> listEvent() {
        return iEventService.listEvent();
    }

    @DeleteMapping("/event/delete/{id}")
    public void deleteEvent( @PathVariable int id) {
        iEventService.deleteEvent(id);
    }
    @PostMapping("/event/archiver")
    public void archiveEvents() {
        iEventService.archiveEvents();
    }
    @GetMapping("event/{eventType}")
    public Long  NumberOfEvents(@PathVariable EventType eventType) {
        return iEventService.NumberOfEvents(eventType);
    }
    @GetMapping("event/date/{eventDate}")
    public List<Event> findByEventDate(@PathVariable("eventDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date eventDate) {
        return iEventService.findByEventDate(eventDate);
    }
    @GetMapping("event/place/{place}")
    public List<Event> findByPlace(  @PathVariable String place) {
        return iEventService.findByPlace(place);
    }
}
