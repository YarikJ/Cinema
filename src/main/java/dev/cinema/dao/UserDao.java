package dev.cinema.dao;

import dev.cinema.models.User;

public interface UserDao {
    User add(User user);

    User findByEmail(String email);
}
