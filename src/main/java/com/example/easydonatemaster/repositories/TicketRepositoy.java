package com.example.easydonatemaster.repositories;

import com.example.easydonatemaster.entites.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepositoy extends JpaRepository<Ticket, Integer> {
}
