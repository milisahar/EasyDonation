package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;

import javax.mail.MessagingException;

public interface IEmailService {
    String sendSimpleMail(Ticket ticket);
}
