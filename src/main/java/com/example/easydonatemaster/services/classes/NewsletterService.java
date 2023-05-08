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
    EmailService emailService;

    @Autowired
    TemplateEngine templateEngine;

    private static final Logger logger = LoggerFactory.getLogger(NewsletterService.class);


    //@Scheduled(cron = "0 * * * * *", zone = "Europe/Paris")
    public void sendNewsletter() {
        logger.info("Sending newsletter...");
        List<Article> articles = articleRepository.findNewArticlesSinceLastSent();
        logger.debug("Found {} new articles since last sent", articles.size());
        if (!articles.isEmpty()) {
            String newsletterHtml = createNewsletterHtml(articles);
            emailService.sendNewsletter("farahchouikh@esprit.tn", "New articles", newsletterHtml);
            logger.debug("Sent newsletter email to farahchouikh@esprit.tn with {} new articles", articles.size());
            articleRepository.updateLastSentDate(LocalDateTime.now()); // pass the current date and time as argument
            logger.debug("Updated last sent date to {}", LocalDateTime.now());
        } else {
            logger.debug("No new articles found since last sent");
        }
        logger.info("Newsletter sent.");

    }

    private String createNewsletterHtml(List<Article> articles) {
         Context context = new Context();
        context.setVariable("articles", articles);
        return templateEngine.process("newsletter-template", context);
    }

}


