package dev.cinema.models.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class OrderRequestDto {
    @NotNull
    @Email(regexp = "^(.+)@(.+)$")
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
