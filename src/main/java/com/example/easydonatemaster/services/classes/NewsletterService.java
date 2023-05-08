package com.example.easydonatemaster.services.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.easydonatemaster.repositories.ArticleRepositoy;
import com.example.easydonatemaster.entites.Article;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class NewsletterService {

    @Autowired
    ArticleRepositoy articleRepository;


    @Autowired
    TemplateEngine templateEngine;

    private static final Logger logger = LoggerFactory.getLogger(NewsletterService.class);




    private String createNewsletterHtml(List<Article> articles) {
         Context context = new Context();
        context.setVariable("articles", articles);
        return templateEngine.process("newsletter-template", context);
    }

}


