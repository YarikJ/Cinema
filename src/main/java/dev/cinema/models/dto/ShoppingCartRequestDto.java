package dev.cinema.models.dto;

import java.util.List;

public class ShoppingCartRequestDto {
    private String userEmail;
    private List<TicketDto> dtoTickets;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public List<TicketDto> getDtoTickets() {
        return dtoTickets;
    }

    public void setDtoTickets(List<TicketDto> dtoTickets) {
        this.dtoTickets = dtoTickets;
    }
}
