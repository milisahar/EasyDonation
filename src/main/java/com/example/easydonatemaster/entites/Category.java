package com.example.easydonatemaster.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
@JsonIgnore
    @ElementCollection
    @CollectionTable(name = "category_tags", joinColumns = @JoinColumn(name = "category_id"))
    @Column(name = "tag")
    private List<String> tags;
@JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Article> articles;

    // constructors, getters, setters, and other methods

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }



    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;

    }

    public Category(Long id, String name, List<String> tags, List<Article> articles) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.articles = articles;
    }

    public Category(String name, List<String> tags) {
        this.name = name;
        this.tags = tags;
    }
    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                ", articles=" + articles +
                '}';
    }
}