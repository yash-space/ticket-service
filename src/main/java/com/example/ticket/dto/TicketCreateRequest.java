package com.example.ticket.dto;

import jakarta.validation.constraints.NotNull;

public class TicketCreateRequest {

    @NotNull(message = "assetId is required")
    private Long assetId;

    private String description;
    private String priority;

    public TicketCreateRequest() {}

    public Long getAssetId() { return assetId; }
    public void setAssetId(Long assetId) { this.assetId = assetId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

}
