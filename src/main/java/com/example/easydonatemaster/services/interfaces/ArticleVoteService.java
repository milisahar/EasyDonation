package com.example.easydonatemaster.services.interfaces;

import com.example.easydonatemaster.entites.ArticleVote;

import java.util.List;

public interface ArticleVoteService {



    List<ArticleVote> retrieveAllArticleVotes();
    ArticleVote addArticleVote(ArticleVote av);
    ArticleVote updateArticleVote(ArticleVote av);
    ArticleVote retrieveArticleVote(Integer id);
    ArticleVote removeArticleVote(Integer id);
}
