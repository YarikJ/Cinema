package dev.cinema.service;

import dev.cinema.exceptions.AuthenticationException;
import dev.cinema.models.User;

public interface AuthenticationService {

    User login(String email, String password) throws AuthenticationException;

    User register(String email, String password);
}
