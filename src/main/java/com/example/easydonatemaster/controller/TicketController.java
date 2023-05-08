package com.example.easydonatemaster.controller;

import com.example.easydonatemaster.entites.Ticket;
import com.example.easydonatemaster.services.IEmailService;
import com.example.easydonatemaster.services.ITicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
public class TicketController {
    private ITicketService iTicketService;
    private IEmailService iEmailService;
    @PostMapping("/ticket/add")
    Ticket addTicket( @RequestBody Ticket ticket) {
        iEmailService.sendSimpleMail(ticket);
        return iTicketService.addTicket(ticket);
    }
    @PostMapping("/ticket/update")
    Ticket updateTicket(@RequestBody Ticket ticket) {
        return iTicketService.updateTicket(ticket); }
    @GetMapping("ticket/list")
    List<Ticket> ListTicket() {
        return iTicketService.ListTicket(); }
    @DeleteMapping("ticket/delete/{id}")
    void deleteTicket( @PathVariable int id) {
        iTicketService.deleteTicket(id); }
    }
