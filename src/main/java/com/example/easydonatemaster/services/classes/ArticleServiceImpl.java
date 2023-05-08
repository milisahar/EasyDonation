package com.example.easydonatemaster.services.classes;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.repositories.ArticleRepositoy;
import com.example.easydonatemaster.services.interfaces.ArticleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleRepositoy ar ;


    @Override

    public Article retrieveMostLikedArticle() {
        return ar.findAll(Sort.by(Sort.Direction.DESC, "likes")).stream().findFirst().orElse(null);
    }



    @Override
    public List<ArticleComment> getAllCommentsByArticle(int id) {
        Article article =ar.findById(id).orElse(null);
        Set<ArticleComment> articleComments= (Set<ArticleComment>) article.getArticleComments();
        List<ArticleComment> listArticleComments=new ArrayList<>();
        for (ArticleComment  ac : articleComments){
            listArticleComments.add(ac);
        }
        return listArticleComments;
    }

    @Override
    public List<Article> retrieveAllArticles() {
        List<Article> articles = (List<Article>) ar.findAll();
        return articles;
    }


    @Override
    public Article addArticle(Article a)  {
        if ( a.getTitle() == null || a.getTitle().isEmpty()){
            throw new IllegalArgumentException(" Article doit avoir un titre ");
        }
        try {
            return ar.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Peut pas ajouter article", e);
        }

    }

    @Override
    public Article updateArticle(Article a) {

        return ar.save(a);
    }

    @Override
    public Article retrieveArticle(Integer id) {
        return ar.findById(id).get();
    }

    @Override
    public Article removeArticle(Integer id) {
        Article article= ar.findById(id).get();
        ar.deleteById(id);
        return article;
    }




    @Override
    public void likeArticle(Integer id) {
        Optional<Article> articleOptional = ar.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            int likes = article.getLikes();
            article.setLikes(likes + 1);
            ar.save(article);
        }
    }

    @Override
    public void dislikeArticle(Integer id) {
        Optional<Article> articleOptional = ar.findById(id);
        if (articleOptional.isPresent()) {
            Article article = articleOptional.get();
            int dislikes = article.getDislikes();
            article.setDislikes(dislikes + 1);
            ar.save(article);
        }
    }

    @Override
    public List<ArticleComment> getAllCommentsForArticle(Integer id) {
        Article article = ar.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));
        return article.getArticleComments();
    }


    @Override
    public Article getArticleWithMostComments() {
        int maxComments = 0;
        Article articleWithMostComments = null;

        List<Article> articles = ar.findAll();
        for (Article article : articles) {
            int commentCount = article.getArticleComments().size();

            if (commentCount > maxComments) {
                maxComments = commentCount;
                articleWithMostComments = article;
            }
        }

        return articleWithMostComments;
    }
    public List<Article> getArticlesByCategory(String categoryName) {
        log.debug("Inside getArticlesByCategory method");
        List<Article> articles = ar.findAll();
        log.debug("Retrieved all articles: {}", articles);
        List<Article> articlesByCategory = new ArrayList<>();
        for (Article article : articles) {
            Category articleCategory = article.getCategory();
            if (articleCategory != null && articleCategory.getName().equals(categoryName)) {
                log.debug("Adding article to category: {}", article);
                articlesByCategory.add(article);
            } else {
                log.debug("Skipping article: {}", article);
            }
        }

        log.debug("Returning articles by category: {}", articlesByCategory);
        return articlesByCategory;
    }

    @Override
    public void addImage(int id, MultipartFile image) throws IOException {
        Article article = ar.findById(id).orElse(null);

        String filename = StringUtils.cleanPath(image.getOriginalFilename());
        if(filename.contains("..")){
            System.out.println("!!! Not a valid File");
        }
        article.setImg(Base64.getEncoder().encodeToString(image.getBytes()));

        ar.save(article);
    }
    @Override
    public Map.Entry<String, Integer> getCategoryNameWithMostArticles() {
        Map<String, Integer> categoryNameCounts = new HashMap<>();

        List<Article> articles = ar.findAll();
        for (Article article : articles) {
            Category category = article.getCategory();
            if (category != null) {
                String categoryName = category.getName();
                categoryNameCounts.merge(categoryName, 1, Integer::sum);
            }
        }

        Map.Entry<String, Integer> categoryNameWithMostArticles = null;
        for (Map.Entry<String, Integer> entry : categoryNameCounts.entrySet()) {
            if (categoryNameWithMostArticles == null || entry.getValue() > categoryNameWithMostArticles.getValue()) {
                categoryNameWithMostArticles = entry;
            }
        }

        return categoryNameWithMostArticles;
    }


}
