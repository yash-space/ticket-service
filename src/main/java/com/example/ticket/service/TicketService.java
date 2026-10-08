package com.example.ticket.service;

import com.example.ticket.entity.Ticket;
import com.example.ticket.entity.TicketStatus;

import java.util.List;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Ticket getTicketById(Long id);
    List<Ticket> getAllTickets();
    Ticket updateStatus(Long id, TicketStatus newStatus);
    void deleteTicket(Long id);
}
