package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.EventComment;
import com.example.easydonatemaster.repositories.EventCommentRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventCommentService implements  IEventCommentService{
EventCommentRepositoy eventCommentRepositoy;

    public EventCommentService(EventCommentRepositoy eventCommentRepositoy) {
        this.eventCommentRepositoy = eventCommentRepositoy;
    }

    @Override
    public EventComment addEventComment(EventComment eventComment) {
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
