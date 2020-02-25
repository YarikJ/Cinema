package dev.cinema.service.impl;

import dev.cinema.dao.OrderDao;
import dev.cinema.models.Order;
import dev.cinema.models.Ticket;
import dev.cinema.models.User;
import dev.cinema.service.OrderService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        Order order = new Order();
        order.setTickets(tickets);
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        return orderDao.completeOrder(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
