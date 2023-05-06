package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface ArticleRepositoy extends JpaRepository<Article, Integer> {

    @Query("SELECT a FROM Article a WHERE a.publishDate > a.lastSentDate")
    List<Article> findNewArticlesSinceLastSent();

    @Modifying
    @Query("UPDATE Article a SET a.lastSentDate = :currentDate")
    void updateLastSentDate(@Param("currentDate") LocalDateTime currentDate);
    @Modifying
    @Query("update Article a set a.likes = a.likes + 1 where a.id = :id")
    void incrementLikes(@Param("id") Integer articleId);


    @Modifying
    @Query("update Article a set a.dislikes = a.dislikes + 1 where a.id = :id")
    void incrementDislikes(@Param("id") Integer articleId);

    default void assignCategory(Article article, List<Category> categories) {
        article.assignCategory(categories);
        save(article);
    }

    default void assignRandomCategory(Article article, List<Category> categories) {
        article.assignRandomCategory(categories);
        save(article);
    }

    List<Article> findByTagsIn(List<String> tags);

    @Query("SELECT a FROM Article a JOIN a.category c WHERE c.name = :categoryName")
    List<Article> findByCategoryName(@Param("categoryName") String categoryName);
}

