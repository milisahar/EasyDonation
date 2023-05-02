package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class EmailService implements  IEmailService  {
    private JavaMailSender javaMailSender;
    public String sendSimpleMail( Ticket ticket) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            //  mailMessage.setFrom(sender);
            mailMessage.setTo(ticket.getOwner().getEmail());
            mailMessage.setSubject(ticket.getEventRef().getTitle()+" ticket !");
            mailMessage.setText("Vous trouverz ci-joint votre ticket de l'evenement :"+ ticket.getReference());

            javaMailSender.send(mailMessage);
            return "Mails sent successfully !";
        }
        catch (Exception e){
            return "Error while sending Mail";
        }
    }

}
