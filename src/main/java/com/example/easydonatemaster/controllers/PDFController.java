package com.example.easydonatemaster.controllers;

import com.example.easydonatemaster.services.classes.EmailService;
import com.example.easydonatemaster.services.classes.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("")
public class PDFController {


    @Autowired
    EmailService es;

    @GetMapping("/send-email")
    public void sendEmail(HttpServletResponse response) throws IOException {
        String to = "farah.chouikh@esprit.com";
        String subject = "Test Email";
        String htmlContent = "<h1>This is a test email</h1>";
        es.sendTestEmail(to, subject, htmlContent);
        response.getWriter().print("Email sent successfully");
    }
}
