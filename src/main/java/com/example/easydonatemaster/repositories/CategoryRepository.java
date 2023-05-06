package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.name = ?1")
    Category findByName(String name);
    List<Category> findByTagsIn(List<String> tags);




}
