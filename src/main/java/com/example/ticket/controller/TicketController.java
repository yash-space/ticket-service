package com.example.ticket.controller;

import com.example.ticket.dto.StatusUpdateRequest;
import com.example.ticket.dto.TicketCreateRequest;
import com.example.ticket.dto.TicketResponse;
import com.example.ticket.entity.Ticket;
import com.example.ticket.mapper.TicketMapper;
import com.example.ticket.service.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<TicketResponse> createTicket(@Valid @RequestBody TicketCreateRequest request) {
        Ticket created = ticketService.createTicket(TicketMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(TicketMapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketResponse> getTicket(@PathVariable Long id) {
        Ticket ticket = ticketService.getTicketById(id);
        return ResponseEntity.ok(TicketMapper.toResponse(ticket));
    }

    @GetMapping
    public ResponseEntity<List<TicketResponse>> getAllTickets() {
        List<TicketResponse> tickets = ticketService.getAllTickets()
                .stream()
                .map(TicketMapper::toResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(tickets);
    }

    @PatchMapping("{id}/status")
    public ResponseEntity<TicketResponse> updateStatus(@PathVariable Long id,
                                                       @Valid @RequestBody StatusUpdateRequest request) {
        Ticket updated = ticketService.updateStatus(id, request.getStatus());
        return ResponseEntity.ok(TicketMapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
