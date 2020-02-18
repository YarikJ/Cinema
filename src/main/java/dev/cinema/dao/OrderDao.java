package dev.cinema.dao;

import dev.cinema.models.Order;
import dev.cinema.models.User;

import java.util.List;

public interface OrderDao {

    Order completeOrder(Order order);

    List<Order> getOrderHistory(User user);
}
