package com.example.ticket.service;

import com.example.ticket.entity.Ticket;
import com.example.ticket.entity.TicketStatus;
import com.example.ticket.exception.InvalidStatusTransitionException;
import com.example.ticket.exception.TicketNotFoundException;
import com.example.ticket.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    // Defines which status can legally move to which next status
    private static final Map<TicketStatus, List<TicketStatus>> ALLOWER_TRANSITIONS = Map.of(
            TicketStatus.NEW, List.of(TicketStatus.IN_PROGRESS),
            TicketStatus.IN_PROGRESS, List.of(TicketStatus.RESOLVED),
            TicketStatus.RESOLVED, List.of(TicketStatus.CLOSED, TicketStatus.IN_PROGRESS),
            TicketStatus.CLOSED, List.of()
    );

    @Override
    public Ticket createTicket(Ticket ticket) {
        // status defaults to NEW via @PrePersist on the entity itself
        return ticketRepository.save(ticket);
    }

    @Override
    public Ticket getTicketById(Long id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket updateStatus(Long id, TicketStatus newStatus) {
        Ticket ticket = getTicketById(id); // reuse - don't duplicate the lookup logic
        TicketStatus currentStatus = ticket.getStatus();
        List<TicketStatus> allowedNext = ALLOWER_TRANSITIONS.get(currentStatus);

        if (!allowedNext.contains(newStatus)) {
            throw new InvalidStatusTransitionException(
                    "Cannot move ticket from " + currentStatus + " to " +newStatus
            );
        }

        ticket.setStatus(newStatus);
        return ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(Long id) {
        if (!ticketRepository.existsById(id)) {
            throw new TicketNotFoundException(id);
        }
        ticketRepository.deleteById(id);
    }

}
