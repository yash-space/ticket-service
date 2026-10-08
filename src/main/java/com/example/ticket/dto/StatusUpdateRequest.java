package com.example.ticket.dto;

import com.example.ticket.entity.TicketStatus;
import jakarta.validation.constraints.NotNull;

public class StatusUpdateRequest {
    @NotNull(message = "status is required")
    private TicketStatus status;

    public StatusUpdateRequest() {}
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
}
