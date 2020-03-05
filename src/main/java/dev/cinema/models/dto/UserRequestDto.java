package dev.cinema.models.dto;

import dev.cinema.annotations.FieldsValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword"
)
public class UserRequestDto {
    @NotNull(message = "enter email")
    @Email(regexp = "^(.+)@(.+)$")
    private String email;
    @NotNull(message = "enter password")
    private String password;
    @NotNull(message = "confirm password")
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
