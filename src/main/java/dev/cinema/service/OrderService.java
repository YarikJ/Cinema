package dev.cinema.service;

import dev.cinema.models.Order;
import dev.cinema.models.Ticket;
import dev.cinema.models.User;

import java.util.List;

public interface OrderService {

    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
