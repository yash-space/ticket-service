package com.example.ticket;

import com.example.ticket.entity.Ticket;
import com.example.ticket.entity.TicketStatus;
import com.example.ticket.exception.InvalidStatusTransitionException;
import com.example.ticket.exception.TicketNotFoundException;
import com.example.ticket.repository.TicketRepository;
import com.example.ticket.service.TicketServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TicketServiceImplTest {

    @Mock
    private TicketRepository ticketRepository;

    private TicketServiceImpl ticketService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ticketService = new TicketServiceImpl(ticketRepository);
    }

    @Test
    void createTicket_savesAndReturnTicket() {
        Ticket ticket = new Ticket();
        ticket.setAssetId(101L);
        when(ticketRepository.save(ticket)).thenReturn(ticket);

        Ticket result = ticketService.createTicket(ticket);

        assertEquals(101L, result.getAssetId());
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    void getTicketById_whenNotFound_throwsException() {
        when(ticketRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.getTicketById(999L));
    }

    @Test
    void updateStatus_validTransition_succeeds() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setStatus(TicketStatus.NEW);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(ticket);

        Ticket result = ticketService.updateStatus(1L, TicketStatus.IN_PROGRESS);

        assertEquals(TicketStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    void updateStatus_invalidTransition_throwsException() {
        Ticket ticket = new Ticket();
        ticket.setId(1L);
        ticket.setStatus(TicketStatus.CLOSED);
        when(ticketRepository.findById(1L)).thenReturn(Optional.of(ticket));

        assertThrows(InvalidStatusTransitionException.class,
                () -> ticketService.updateStatus(1L, TicketStatus.IN_PROGRESS));

        // The key assertion: save should NEVER be called on an invalid transition
        verify(ticketRepository, never()).save(any());
    }

    @Test
    void deleteTicket_whenNotFound_throwsException() {
        when(ticketRepository.existsById(999L)).thenReturn(false);

        assertThrows(TicketNotFoundException.class,
                () -> ticketService.deleteTicket(999L));
    }

}
