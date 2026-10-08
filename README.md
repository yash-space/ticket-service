# Ticket Service

Part of the Smart Asset Health & Predictive Maintenance Platform — a microservices-based
system for ingesting IoT sensor alarms and managing maintenance tickets.

## What this service does
Manages the lifecycle of maintenance tickets (NEW → IN_PROGRESS → RESOLVED → CLOSED),
enforcing valid status transitions as a business rule in the service layer.

## Stack
- Java 21, Spring Boot, Spring Data JPA
- PostgreSQL (hosted on Neon)
- JUnit 5 + Mockito for unit testing

## Architecture notes
- Interface-driven service layer (`TicketService` / `TicketServiceImpl`) for testability
  and separation of contract from implementation.
- DTOs separate the public API shape from the persistence model.
- Centralized exception handling (`@RestControllerAdvice`) maps domain exceptions to
  correct HTTP status codes.

## Running locally
1. Set `spring.datasource.url` / username / password in `application.properties` to your
   Postgres instance.
2. `./mvnw spring-boot:run`
3. API available at `http://localhost:8080/api/tickets`

## API endpoints
| Method | Path | Description |
|---|---|---|
| POST | /api/tickets | Create a ticket |
| GET | /api/tickets | List all tickets |
| GET | /api/tickets/{id} | Get one ticket |
| PATCH | /api/tickets/{id}/status | Update ticket status |
| DELETE | /api/tickets/{id} | Delete a ticket |