package com.example.ticket.mapper;

import com.example.ticket.dto.TicketCreateRequest;
import com.example.ticket.dto.TicketResponse;
import com.example.ticket.entity.Ticket;

public class TicketMapper {

    public static Ticket toEntity(TicketCreateRequest request) {
        Ticket ticket = new Ticket();
        ticket.setAssetId((request.getAssetId()));
        ticket.setDescription((request.getDescription()));
        ticket.setPriority((request.getPriority()));
        return ticket;
    }

    public static TicketResponse toResponse(Ticket ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getAssetId(),
                ticket.getDescription(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreatedDate(),
                ticket.getUpdatedDate()
        );
    }
}
