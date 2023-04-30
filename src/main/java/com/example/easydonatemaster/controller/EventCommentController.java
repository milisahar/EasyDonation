package com.example.easydonatemaster.controller;


import com.example.easydonatemaster.entites.EventComment;
import com.example.easydonatemaster.services.IEventCommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventCommentController {
    IEventCommentService iEventCommentService;

    public EventCommentController(IEventCommentService iEventCommentService) {
        this.iEventCommentService = iEventCommentService;
    }
    @PostMapping("/eventComment/add")
    EventComment addEventComment( @RequestBody EventComment eventComment) {
        return iEventCommentService.addEventComment(eventComment);
    }
   @PostMapping("eventComment/update/{id}")
    EventComment updateEventComment( @RequestBody EventComment eventComment) {
        return iEventCommentService.updateEventComment(eventComment); }
    @GetMapping("eventComment/List")
     List<EventComment> listEventComment() {

        return iEventCommentService.listEventComment();
    }
    @DeleteMapping("eventComment/delete/{id}")
    void deleteEeventComment(@PathVariable int id) {
    iEventCommentService.deleteEeventComment(id);
    }
    }
