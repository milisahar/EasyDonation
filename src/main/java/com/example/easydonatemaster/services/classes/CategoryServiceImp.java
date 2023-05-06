package com.example.easydonatemaster.services.classes;

import com.example.easydonatemaster.entites.Article;
import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.repositories.CategoryRepository;
import com.example.easydonatemaster.services.interfaces.CategoryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class CategoryServiceImp implements CategoryService {


   @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> matchArticleToCategory(Article article) {
        List<Category> matchedCategories = new ArrayList<>();

        log.info("Matching article {} to categories", article.getId());

        List<Category> categories = categoryRepository.findAll();
        for (Category category : categories) {
            int matchedTags = 0;
            for (String tag : article.getTags()) {
                if (category.getTags().contains(tag)) {
                    matchedTags++;
                }
            }
            double score = (double) matchedTags / category.getTags().size();
            if (score > 0.5) {
                matchedCategories.add(category);
            }
        }

        log.info("Matched categories: {}", matchedCategories);

        return matchedCategories;
    }
@Override
    public void save(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    public void deleteCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if (category != null) {
            categoryRepository.delete(category);
        }
    }
@Override

    public List<Category> getAllDistinctCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<Category> distinctCategories = distinctByName(categories);
        return distinctCategories;
    }

    private List<Category> distinctByName(List<Category> categories) {
        return categories.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Category::getName))), ArrayList::new));
    }
}
