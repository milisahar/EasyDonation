package com.example.easydonatemaster.dto;
import com.example.easydonatemaster.entites.Article;

import javax.persistence.Id;
import java.util.List;

public class ArticleTagsDTO {
    private Article article;
    @Id
    private  Integer id;
    private List<String> commonTags;

    private String title;

    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArticleTagsDTO(Article article, List<String> commonTags , String title , String description, Integer id) {
        this.article = article;
        this.commonTags = commonTags;
        this.title = article.getTitle();
        this.description = article.getDescription(); this.id= article.getId();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<String> getCommonTags() {
        return commonTags;
    }

    public void setCommonTags(List<String> commonTags) {
        this.commonTags = commonTags;
    }
}
