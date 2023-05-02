package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;

public interface IEmailService {
    String sendSimpleMail(Ticket ticket);
}
