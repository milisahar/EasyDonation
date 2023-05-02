package com.example.easydonatemaster.controller;


import com.example.easydonatemaster.entites.EventComment;
import com.example.easydonatemaster.services.IEventCommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class EventCommentController {
    IEventCommentService iEventCommentService;

    public EventCommentController(IEventCommentService iEventCommentService) {
        this.iEventCommentService = iEventCommentService;
    }
        @PostMapping("/eventComment/add/{id}")
    EventComment addEventComment( @RequestBody EventComment eventComment ,@PathVariable int id) {
        return iEventCommentService.addEventComment(eventComment,id);
    }
   @PostMapping("/eventComment/update/{id}")
    EventComment updateEventComment( @RequestBody EventComment eventComment) {
        return iEventCommentService.updateEventComment(eventComment); }
    @GetMapping("/eventComment/List")
     List<EventComment> listEventComment() {

        return iEventCommentService.listEventComment();
    }
    @DeleteMapping("/eventComment/delete/{id}")
    void deleteEeventComment(@PathVariable int id) {
    iEventCommentService.deleteEeventComment(id);
    }
    }
