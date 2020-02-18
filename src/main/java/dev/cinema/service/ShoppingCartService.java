package dev.cinema.service;

import dev.cinema.models.MovieSession;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;

public interface ShoppingCartService {

    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);

    void clear(ShoppingCart shoppingCart);
}
