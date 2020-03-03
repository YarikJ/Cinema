package dev.cinema.models.dto;

import dev.cinema.annotations.FieldsValueMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @NotNull
    @Email(regexp = "^(.+)@(.+)$")
    private String email;
    @NotNull
    private String password;
    @NotNull
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
