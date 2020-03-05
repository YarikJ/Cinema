package dev.cinema.service;

import dev.cinema.models.User;

public interface AuthenticationService {

    User register(String email, String password);
}
