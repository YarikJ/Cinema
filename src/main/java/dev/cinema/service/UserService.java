package dev.cinema.service;

import dev.cinema.models.User;

public interface UserService {
    User add(User user);

    User get(Long userId);

    User findByEmail(String email);
}
