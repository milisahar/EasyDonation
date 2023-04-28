package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepositoy extends JpaRepository<Article, Integer> {
}
