package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.services.IEventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventsController {
    IEventService iEventService;

    public EventsController(IEventService iEventService) {
        this.iEventService = iEventService;
    }

    @PostMapping("/event/add")
    Event addEvent(@RequestBody Event event) {
        return iEventService.addEvent(event);
    }

    @PostMapping("/event/update/{id}")
    Event updateEvent(@RequestBody Event event) {
        return iEventService.updateEvent(event);
    }

    @GetMapping("event/listEvent")
    public List<Event> listEvent() {
        return iEventService.listEvent();
    }

    @DeleteMapping("/event/delete/{id}")
    public void deleteEvent( @PathVariable int id) {
        iEventService.deleteEvent(id);
    }
}
