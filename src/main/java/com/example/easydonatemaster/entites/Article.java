package com.example.easydonatemaster.entites;

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
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String img;
    private String content;
    @ManyToOne
    private User publisher;
    @OneToMany(mappedBy = "article")
    private List<ArticleVote> articleVotes;
    @OneToMany(mappedBy = "article" , cascade = CascadeType.ALL)
    private List <ArticleComment> articleComments;
}
