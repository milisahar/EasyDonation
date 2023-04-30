package com.example.easydonatemaster.services;

import com.example.easydonatemaster.entites.Ticket;
import com.example.easydonatemaster.repositories.TicketRepositoy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService implements ITicketService{
    private TicketRepositoy ticketRepositoy;

    public TicketService(TicketRepositoy ticketRepositoy) {
        this.ticketRepositoy = ticketRepositoy;
    }

    @Override
    public Ticket addTicket(Ticket ticket) {
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
