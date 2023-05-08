package com.example.easydonatemaster.services.interfaces;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ArticleService {
    Article retrieveMostLikedArticle();


    List<ArticleComment> getAllCommentsByArticle(int id);
    List<Article> retrieveAllArticles();
    Article addArticle(Article a);
    Article updateArticle(Article a);
    Article retrieveArticle(Integer id);
    Article removeArticle(Integer id);

    public  void likeArticle(Integer id);
    public void dislikeArticle(Integer id);
    public List<ArticleComment> getAllCommentsForArticle(Integer id);

    public void uploadImage(Integer id, MultipartFile img);

    Article getArticleWithMostComments();

    public List<Article> getArticlesByCategory(String categoryName);

}
