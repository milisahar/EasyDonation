package com.example.easydonatemaster.services.interfaces;

import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.entites.Article;

import java.util.List;


public interface CategoryService {

    List<Category> matchArticleToCategory(Article article);

    void save(Category category);

    Category getCategoryByName(String name);


    List<Category> findAll();
    public void deleteCategoryById(Long id);
    void delete(Category category);
    public List<Category> getAllDistinctCategories();


}
