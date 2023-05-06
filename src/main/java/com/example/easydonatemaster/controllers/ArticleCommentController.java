package com.example.easydonatemaster.controllers;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import com.example.easydonatemaster.repositories.ArticleRepositoy;
import com.example.easydonatemaster.services.interfaces.ArticleCommentService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.ws.Response;
import java.util.List;
@RestController
@RequestMapping("/ControllerArticleComment")
@CrossOrigin (origins = "http://localhost:4200")


public class ArticleCommentController {
    @Autowired
    ArticleCommentService acs;
    @Autowired
    ArticleRepositoy ar;

    @GetMapping("/displayArticleComment/{id}")
    public ArticleComment displayArticle(@PathVariable("id") int id) {
        return acs.retrieveArticleComment(id);
    }

    @GetMapping("/displayArticleComments")
    public List<ArticleComment> displayAllArticleComments(){
        return acs.retrieveAllArticleComments();
    }

    @PostMapping("/addArticleComment/{id}")
        public ArticleComment addArticleComment(@PathVariable Integer id, @RequestBody ArticleComment articleComment) {
        Article article = ar.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Article not found"));

        articleComment.setArticle(article);

        return acs.addArticleComment(articleComment, article);
    }

    @PutMapping("/addBoost/{id}")
    public ArticleComment addBoost(@PathVariable Integer id) {
        return acs.addBoost(id);
    }



    @PutMapping("/updateArticleComment/{id}")
    public ArticleComment updateArticleComment (@PathVariable Integer id, @RequestBody ArticleComment ac) {
        return acs.updateArticleComment(id, ac);
    }

    @DeleteMapping("/deleteArticleComment/{id}")
    public ResponseEntity<String> deleteArticleComment(@PathVariable("id")int id) {
        try {
            acs.removeArticleComment(id);
            return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/displayArticleCommentsByArticle/{articleId}")
    public List<ArticleComment> displayArticleCommentsByArticle(@PathVariable("articleId") int articleId) {
        return acs.retrieveArticleCommentsByArticle(articleId);
    }

}
