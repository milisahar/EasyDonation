package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleCommentRepositoy extends JpaRepository<ArticleComment,Integer> {
}
