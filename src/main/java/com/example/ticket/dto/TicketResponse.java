package com.example.ticket.dto;

import com.example.ticket.entity.TicketStatus;
import java.time.LocalDateTime;

public class TicketResponse {
    private Long id;
    private Long assetId;
    private String description;
    private String priority;
    private TicketStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public TicketResponse() {}

    public TicketResponse(Long id, Long assetId, String description, String priority,
                          TicketStatus status, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.assetId = assetId;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    public TicketStatus getStatus() { return status; }
    public void setStatus(TicketStatus status) { this.status = status; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public LocalDateTime getUpdatedDate() { return updatedDate; }
    public void setUpdatedDate(LocalDateTime updatedDate) { this.updatedDate = updatedDate; }
}
