package com.example.easydonatemaster.controllers;

import com.example.easydonatemaster.entites.Category;
import com.example.easydonatemaster.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins ="http://localhost:4200")

@Configuration
public class CategoryConfig {

@Autowired


private CategoryService categoryService;

    @PostConstruct
    public void init() {
       Category[] categories = {
                new Category("Poverty", Arrays.asList("poverty reduction", "economic inequality", "social welfare", "financial inclusion", "development aid")),
                new Category("Education", Arrays.asList("K-12 education", "higher education", "online learning", "curriculum development", "student engagement")),
                new Category("Environment", Arrays.asList("climate change", "renewable energy", "sustainable development", "conservation", "biodiversity")),
                new Category("Children", Arrays.asList("child welfare", "child development", "early childhood education", "child protection", "children's rights")),
                new Category("Pollution", Arrays.asList("air pollution", "water pollution", "waste management", "environmental regulations", "emissions reduction")),
                new Category("Peace", Arrays.asList("conflict resolution", "peacebuilding", "diplomacy", "international security", "disarmament")),
                new Category("Politics", Arrays.asList("political science", "government policies", "democracy", "civic engagement", "political campaigns")),
                new Category("Opinion", Arrays.asList("editorial opinions", "opinion polls", "public opinion", "media analysis", "commentary")),
                new Category("Science", Arrays.asList("scientific research", "scientific discovery", "scientific communication", "science education", "science policy")),
                new Category("Health", Arrays.asList("healthcare systems", "public health", "disease prevention", "medical research", "healthcare policy"))
        };
        for (Category category : categories) {
           List<String> tags = category.getTags();
            System.out.println("Category: " + category.getName() + ", Tags: " + tags);
        }
    }

}
