package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Event;
import com.example.easydonatemaster.entites.EventComment;
import com.example.easydonatemaster.repositories.EventCommentRepositoy;
import com.example.easydonatemaster.repositories.EventRepositoy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class EventCommentService implements  IEventCommentService{
    @Autowired
EventCommentRepositoy eventCommentRepositoy;
@Autowired
EventRepositoy eventRepositoy;

    public EventCommentService(EventCommentRepositoy eventCommentRepositoy) {
        this.eventCommentRepositoy = eventCommentRepositoy;
    }

    @Override
    public EventComment addEventComment(EventComment eventComment ,int id) {
        Event event= eventRepositoy.findById(id).get();
        eventComment.setEvent(event);
        return eventCommentRepositoy.save(eventComment);
    }

    @Override
    public EventComment updateEventComment(EventComment eventComment) {
        return eventCommentRepositoy.save(eventComment);

    }

    @Override
    public List<EventComment> listEventComment() {

        return eventCommentRepositoy.findAll();
    }

    @Override
    public void deleteEeventComment(int id) {
        eventCommentRepositoy.deleteById(id);
    }
}
