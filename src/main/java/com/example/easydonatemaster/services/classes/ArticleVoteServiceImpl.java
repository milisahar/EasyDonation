package com.example.easydonatemaster.services.classes;

import com.example.easydonatemaster.entites.ArticleVote;
import com.example.easydonatemaster.repositories.ArticleVoteRepositoy;
import com.example.easydonatemaster.services.interfaces.ArticleVoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ArticleVoteServiceImpl implements ArticleVoteService {

    @Autowired
    ArticleVoteRepositoy avr;


    @Override
    public List<ArticleVote> retrieveAllArticleVotes() {
        List<ArticleVote> articleVotes = (List<ArticleVote>) avr.findAll();
        return articleVotes;
    }

    @Override
    public ArticleVote addArticleVote(ArticleVote av) {
        return avr.save(av);
    }

    @Override
    public ArticleVote updateArticleVote(ArticleVote av) {
        return avr.save(av);
    }

    @Override
    public ArticleVote retrieveArticleVote(Integer id) {
        return avr.findById(id).get();
    }

    @Override
    public ArticleVote removeArticleVote(Integer id) {
       ArticleVote articleVote= avr.findById(id).get();
        avr.deleteById(id);
        return articleVote;
    }
}
