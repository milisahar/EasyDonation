package com.example.easydonatemaster.controllers;

import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.repositories.CategoryRepository;
import com.example.easydonatemaster.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.easydonatemaster.entites.Article;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@CrossOrigin(origins ="http://localhost:4200")
@RestController
@RequestMapping("/Category")

    public class CategoryController {

        @Autowired
        private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    @PostMapping("/matchArticleToCategory")
    public List<Category> matchArticleToCategory(@RequestBody Article article) {
        return categoryService.matchArticleToCategory(article);
    }



    @GetMapping("/displaycategories")
        public List<Category> getAllCategories() {
            return categoryRepository.findAll();
        }



    @GetMapping("/displaytags")
    public ResponseEntity<Set<String>> getTagsCategories() {
        Set<String> tags = new HashSet<>();
        List<Category> categories = categoryService.findAll();
        for (Category category : categories) {
            tags.addAll(category.getTags());
        }
        return ResponseEntity.ok().body(tags);
    }
    @GetMapping("/categories")
    public List<Category> getDistinctCategories() {
        return categoryService.getAllDistinctCategories();
    }
}