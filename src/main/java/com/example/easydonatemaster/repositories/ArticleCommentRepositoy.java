package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleCommentRepositoy extends JpaRepository<ArticleComment,Integer> {

    @Query("SELECT ac FROM ArticleComment ac WHERE ac.article.id =:id")
    List<ArticleComment> CommentByArt(@Param("id") Integer id);

    List<ArticleComment> findByArticleId(Integer id);


}
