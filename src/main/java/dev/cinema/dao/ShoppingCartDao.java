package dev.cinema.dao;

import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;

public interface ShoppingCartDao {

    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);

}
