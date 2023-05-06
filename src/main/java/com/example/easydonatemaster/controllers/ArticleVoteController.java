package com.example.easydonatemaster.controllers;

import com.example.easydonatemaster.entites.ArticleVote;
import com.example.easydonatemaster.services.interfaces.ArticleVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/ControllerArticleVote")

public class ArticleVoteController {
    @Autowired
    ArticleVoteService avs;
    @GetMapping("/displayArticleVote/{id}")
    public ArticleVote displayArticleVote(@PathVariable("id") int id) {
        return avs.retrieveArticleVote(id);
    }
    @GetMapping("/displayArticleVotes")
    public List<ArticleVote> displayAllArticleVotes(){
        return avs.retrieveAllArticleVotes();
    }

    @PostMapping("/addArticleVote")
    public ArticleVote addArticleVote( @RequestBody ArticleVote e) {
        return avs.addArticleVote(e);
    }
    @PutMapping("/updateArticleVote")
    public ArticleVote updateArticleVote(@RequestBody ArticleVote e) {
        return avs.updateArticleVote(e);
    }

    @DeleteMapping("/deleteArticleVote/{id}")
    public void deleteArticleVote(@PathVariable("id")int id) {
        avs.removeArticleVote(id);
    }
}
