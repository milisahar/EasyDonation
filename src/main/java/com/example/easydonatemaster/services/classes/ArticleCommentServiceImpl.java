package com.example.easydonatemaster.services.classes;


import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import com.example.easydonatemaster.repositories.ArticleCommentRepositoy;
import com.example.easydonatemaster.repositories.ArticleRepositoy;
import com.example.easydonatemaster.services.interfaces.ArticleCommentService;
import com.example.easydonatemaster.services.interfaces.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ArticleCommentServiceImpl implements ArticleCommentService {
    @Autowired
    ArticleCommentRepositoy acr;
    @Autowired
    ArticleRepositoy ar;

    @Override
    public List<ArticleComment> retrieveAllArticleComments() {
        List<ArticleComment> articleComments =  (List<ArticleComment>) acr.findAll();
        return articleComments;
    }


    @Override
    public ArticleComment addArticleComment(ArticleComment e, Article article) {
        e.setArticle(article);
        return acr.save(e);
    }

    @Override
    public ArticleComment updateArticleComment(Integer id, ArticleComment ac) {
        return acr.save(ac);
    }

    @Override
    public ArticleComment retrieveArticleComment(Integer id) {
        return acr.findById(id).get();
    }

    @Override
    public List<ArticleComment> retrieveArticleCommentsByArticle(Integer id) {
        return acr.findByArticleId(id);
    }



    @Override
    public void removeArticleComment(Integer id) {

         acr.deleteById(id);
    }

    @Override
    public void assignArticleCommentToComment(Integer ACId, Integer AId) {
        ArticleComment ac =(ArticleComment) acr.findById(ACId).get();
        Article a = ar.findById(AId).get();
        ac.setArticle(a);
        acr.save(ac);
        log.info("articlecomment "+ac.getTitle()+" "+ac.getContent()+" assigné à l article "+a.getTitle());
    }

    @Override
    public List<ArticleComment> getArticleCommentsByArticle(Integer idDepartement) {
        List<ArticleComment> articleCommentss =  acr.CommentByArt(idDepartement);
       return articleCommentss;
    }

    @Override
    public ArticleComment addBoost(Integer id) {
        ArticleComment articleComment = acr.findById(id)
                .orElseThrow(() -> new RuntimeException("ArticleComment not found"));

        if (acr == null) {
            throw new RuntimeException("ArticleCommentRepository not properly initialized");
        }

        Integer boosts = articleComment.getBoosts();
        if (boosts == null) {
            boosts = 0;
        }

        articleComment.setBoosts(boosts + 1);
        return acr.save(articleComment);
    }



}
