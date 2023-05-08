package com.example.easydonatemaster.services.interfaces;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import com.example.easydonatemaster.entites.Category;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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


    Article getArticleWithMostComments();
    public Map.Entry<String, Integer> getCategoryNameWithMostArticles() ;
    public List<Article> getArticlesByCategory(String categoryName);

    public void addImage(int id, MultipartFile image) throws IOException;
}
