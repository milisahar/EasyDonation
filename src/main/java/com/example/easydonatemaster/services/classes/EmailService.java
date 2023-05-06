package com.example.easydonatemaster.services.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    String recipientEmail = "farahchouikh@esprit.tn";

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendNewsletter(String to, String subject, String htmlContent) {
        logger.info("Sending email to {} with subject {}", recipientEmail, subject);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject("UPDATE");
            helper.setText(htmlContent, true);
            mailSender.send(message);
            logger.info("Email sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send email", e);
            throw new RuntimeException("Failed to send email", e);
        }


    }

    public void sendTestEmail(String to, String subject, String htmlContent) {
        logger.info("Sending test email to {} with subject {}", to, subject);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject("whatever");
            helper.setText(htmlContent, true);
            mailSender.send(message);
            logger.info("Test email sent successfully");
        } catch (MessagingException e) {
            logger.error("Failed to send test email", e);
            throw new RuntimeException("Failed to send test email", e);
        }
    }

}
