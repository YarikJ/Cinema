package dev.cinema.models.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ShoppingCartRequestDto {
    @NotNull
    @Email(regexp = "^(.+)@(.+)$")
    private String userEmail;
    @NotNull
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
