package com.example.easydonatemaster.services.interfaces;
import  java.util.List;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;

public interface ArticleCommentService {

    List<ArticleComment> retrieveAllArticleComments();
    public ArticleComment addArticleComment(ArticleComment e, Article article);
    ArticleComment updateArticleComment(Integer id, ArticleComment ac);
    ArticleComment retrieveArticleComment(Integer id);

    List<ArticleComment> retrieveArticleCommentsByArticle(Integer id);

    void removeArticleComment(Integer id);
    public void assignArticleCommentToComment(Integer ACId, Integer  AId) ;
    public  List<ArticleComment> getArticleCommentsByArticle (Integer idDepartement);
    ArticleComment addBoost(Integer id);


}
