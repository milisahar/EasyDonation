package com.example.easydonatemaster.entites;

import com.example.easydonatemaster.profanity.ProfanityFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    @ProfanityFilter
    private String title;
    @ProfanityFilter
    private String description;
    @Column(columnDefinition = "BLOB")
    @Lob
   private String img;
    @ProfanityFilter
    private String content;

    @Column
    private Date publishDate;

    @Column
    private LocalDateTime lastSentDate;
    @Column(name = "likes")
    private int likes;
    @Column(name = "dislikes")
    private int dislikes;


    public void setLastSentDate(LocalDateTime lastSentDate) {
        this.lastSentDate = lastSentDate;
    }



    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }


    @ElementCollection
    @CollectionTable(name = "article_tags", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "tag")
    private List<String> tags;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }



    public LocalDateTime getLastSentDate() {
        return lastSentDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Article(String title, String description, String content) {

    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @ManyToOne
    @JsonIgnore
    private User publisher;

    @OneToMany(mappedBy = "article")
    @JsonIgnore
    private List<ArticleVote> articleVotes;

    @OneToMany(mappedBy = "article" ,  fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List <ArticleComment> articleComments;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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






    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getPublisher() {
        return publisher;
    }

    public String getCategoryName() {
        return category == null ? null : category.getName();
    }


    public void setPublisher(User publisher) {
        this.publisher = publisher;
    }

    public List<ArticleVote> getArticleVotes() {
        return articleVotes;
    }

    public void setArticleVotes(List<ArticleVote> articleVotes) {
        this.articleVotes = articleVotes;
    }

    public List<ArticleComment> getArticleComments() {
        return articleComments;
    }

    public void setArticleComments(List<ArticleComment> articleComments) {
        this.articleComments = articleComments;
    }

    @Transient
    private static final Random random = new Random();

    public void assignCategory(List<Category> categories) {
        Category matchedCategory = categories.stream()
                .filter(category -> !Collections.disjoint(category.getTags(), tags))
                .skip((int) (Math.random() * categories.size()))
                .findFirst()
                .orElse(null);
        category = matchedCategory;
    }


    public void assignRandomCategory(List<Category> categories) {
        // find all categories that match the tags
        List<Category> matchedCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.getTags().containsAll(tags)) {
                matchedCategories.add(category);
            }
        }
        // assign a random category from the matched ones
        if (!matchedCategories.isEmpty()) {
            category = matchedCategories.get(random.nextInt(matchedCategories.size()));
        } else {
            category = null;
        }
    }



}






