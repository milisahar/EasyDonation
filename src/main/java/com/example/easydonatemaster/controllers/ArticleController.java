package com.example.easydonatemaster.controllers;


import com.example.easydonatemaster.dto.ArticleTagsDTO;
import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.ArticleComment;
import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.repositories.ArticleRepositoy;
import com.example.easydonatemaster.repositories.CategoryRepository;
import com.example.easydonatemaster.services.interfaces.ArticleService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import javax.print.Doc;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/ControllerArticle")
public class ArticleController {
    private static final Logger log = LoggerFactory.getLogger(ArticleController.class);

 @Autowired
 ArticleService as;
 @Autowired
 ArticleRepositoy ar;
    @Autowired
    CategoryRepository cr;

    @GetMapping("/displayArticle/{id}")
    public Article displayArticle(@PathVariable("id") int id) {
        return as.retrieveArticle(id);
    }
    @GetMapping("/displayArticles")
    public List<Article> displayAllArticles(){
        return as.retrieveAllArticles();
    }

    @PostMapping("/addArticle")
    public Article addArticle( @RequestBody Article e) {
        return as.addArticle(e);
    }
    @PutMapping("/updateArticle")
    public Article updateArticle(@RequestBody Article e) {
        return as.updateArticle(e);
    }

    @DeleteMapping("/deleteArticle/{id}")
    public void deleteArticle(@PathVariable("id")int id) {
        as.removeArticle(id);
    }



    @GetMapping("/generate-pdf/{id}")
    public ResponseEntity<byte[]> generatePDF(@PathVariable Integer id) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        com.itextpdf.text.Document document = new Document();
        PdfWriter.getInstance(document, baos);
        document.open();
        Article article = ar.findById(id).orElseThrow(() -> new OpenApiResourceNotFoundException("Article not found with ID: " + id));
        Font titleFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD);
        Font contentFont = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);
        Paragraph title = new Paragraph(article.getTitle(), titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        Paragraph description = new Paragraph(article.getDescription(), contentFont);
        description.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(description);
        Paragraph content = new Paragraph(article.getContent(), contentFont);
        content.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(content);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", article.getTitle() + ".pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
        return response;
    }



    @PostMapping("/{id}/like")
    public ResponseEntity<String> likeArticle(@PathVariable Integer id) {
        as.likeArticle(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/dislike")
    public ResponseEntity<String> dislikeArticle(@PathVariable Integer id) {
        as.dislikeArticle(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("article/{id}/comments")
    public List<ArticleComment> getAllCommentsForArticle(@PathVariable Integer id) {
        return as.getAllCommentsForArticle(id);
    }




    @GetMapping("/article/mostLikedArticle")
    public ResponseEntity<Article> retrieveMostLikedArticle(){
        Article mostLikedArticle = as.retrieveMostLikedArticle();
        if (mostLikedArticle == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(mostLikedArticle);
        }



    }

    @PutMapping(path = "/addImage/{id}", consumes = {MULTIPART_FORM_DATA_VALUE})
    public void addImage(@RequestParam MultipartFile file, @PathVariable int id) throws IOException {
        as.addImage(id , file);
    }


    @GetMapping("/articles/most-comments")
    public Article getArticleWithMostComments() {
        return as.getArticleWithMostComments();
    }

    @GetMapping("/tags")
    public List<String> getTags() {
        List<String> tags = new ArrayList<>();
        List<Article> articles = ar.findAll();
        for (Article article : articles) {
            tags.addAll(article.getTags());
        }
        return new ArrayList<>(new HashSet<>(tags)); // remove duplicates and return as list
    }


    @PostMapping("/{id}/assign-category")
    public ResponseEntity<Void> assignCategory(@PathVariable("id") Integer id, @RequestBody List<String> tagNames) {
        Article article = ar.findById(id)
                .orElseThrow(() -> new ResourceAccessException("Article not found with id: " + id));
        List<Category> categories = cr.findByTagsIn(tagNames);
        article.assignCategory(categories);
        ar.save(article);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/assign-random-category")
    public ResponseEntity<Void> assignRandomCategory(@PathVariable("id") Integer id, @RequestBody List<String> tagNames) {
        Article article = ar.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Article not found with id: " + id));
        List<Category> categories = cr.findByTagsIn(tagNames);
        log.info("Categories found: {}", categories);
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("No categories found with tags: " + tagNames);
        }
        Category category = categories.get((int) (Math.random() * categories.size()));
        article.setCategory(category);
        article.getTags().clear(); // clear the existing tags

        article.setTags(tagNames);

        ar.save(article);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getCategoryName/{id}")
    public String getCategoryName(@PathVariable("id") int id) {
        Article article = as.retrieveArticle(id);
        if (article == null) {
            throw new ResourceAccessException("Article not found with ID: " + id);
        }
        Category category = article.getCategory();
        if (category == null) {
            return null;
        }
        return category.getName();
    }

    @GetMapping("/relatedArticles/{id}")
    public List<ArticleTagsDTO> getRelatedArticles(@PathVariable("id") int id) {
        Article article = as.retrieveArticle(id);
        List<String> tags = article.getTags();
        List<Article> relatedArticles = ar.findByTagsIn(tags);

        List<ArticleTagsDTO> articleTagsDTOList = new ArrayList<>();
        for (Article relatedArticle : relatedArticles) {
            List<String> commonTags = new ArrayList<>(tags);
            commonTags.retainAll(relatedArticle.getTags());
            ArticleTagsDTO articleTagsDTO = new ArticleTagsDTO(relatedArticle, commonTags , relatedArticle.getTitle(),
                    relatedArticle.getDescription() , relatedArticle.getId());
            articleTagsDTOList.add(articleTagsDTO);
        }
        return articleTagsDTOList;
    }

    @GetMapping("/articlesbycategory/{categoryName}")
    public List<Article> getArticlesByCategory(@PathVariable String categoryName) {
        return as.getArticlesByCategory(categoryName);
    }

    @GetMapping("/category/most-articles")
    public ResponseEntity<Map<String, Object>> getCategoryWithMostArticles() {
        Map.Entry<String, Integer> category = as.getCategoryNameWithMostArticles();
        if (category != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("categoryName", category.getKey());
            response.put("articleCount", category.getValue());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }

}






}