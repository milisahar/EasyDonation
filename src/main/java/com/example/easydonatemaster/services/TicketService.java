package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;
import com.example.easydonatemaster.repositories.TicketRepositoy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class TicketService implements ITicketService{
    private TicketRepositoy ticketRepositoy;

    @Override
    public Ticket addTicket(Ticket ticket) {

        /* Ticket savedTicket = ticketRepositoy.save(ticket);

        // Envoyer un e-mail
        Email email = new Email();
        email.setTo(savedTicket.getEmail());
        email.setSubject("Nouveau ticket créé");
        email.setMsgBody("Un nouveau ticket a été créé avec l'ID " + savedTicket.getId());
        iEmailService.sendSimpleMail(email);

        return savedTicket;
    } */
        return ticketRepositoy.save(ticket);
    }

    @Override
    public Ticket updateTicket(Ticket ticket) {
        return  ticketRepositoy.save(ticket);
    }

    @Override
    public List<Ticket> ListTicket() {
        return ticketRepositoy.findAll();
    }

    @Override
    public void deleteTicket(int id) {
    ticketRepositoy.deleteById(id);
    }
}
