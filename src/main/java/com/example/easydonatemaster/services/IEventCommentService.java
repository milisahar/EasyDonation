package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.EventComment;

import java.util.List;

public interface IEventCommentService {
    EventComment addEventComment (EventComment eventComment);
    EventComment updateEventComment(EventComment eventComment);
    List<EventComment> listEventComment();
    void deleteEeventComment(int id);
}
