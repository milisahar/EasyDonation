package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;

import java.util.List;

public interface ITicketService {
    Ticket addTicket(Ticket ticket);
    Ticket updateTicket(Ticket ticket);
    List <Ticket> ListTicket();
    void deleteTicket(int id);
}
