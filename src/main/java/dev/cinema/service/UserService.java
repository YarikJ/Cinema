package dev.cinema.service;

import dev.cinema.models.User;

public interface UserService {
    User add(User user);

    User findByEmail(String email);
}
