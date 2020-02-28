package dev.cinema.controllers;

import dev.cinema.models.Order;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.Ticket;
import dev.cinema.models.User;
import dev.cinema.models.dto.OrderRequestDto;
import dev.cinema.models.dto.OrderResponseDto;
import dev.cinema.models.dto.TicketDto;
import dev.cinema.service.OrderService;
import dev.cinema.service.ShoppingCartService;
import dev.cinema.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;
    private ShoppingCartService shoppingCartService;

    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        User user = userService.findByEmail(orderRequestDto.getUserEmail());
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        orderService.completeOrder(shoppingCart.getTickets(), user);
        shoppingCartService.clear(shoppingCart);
    }

    @GetMapping
    public List<OrderResponseDto> getAllOrders(@RequestParam Long userId) {
        User user = userService.get(userId);
        return orderService.getOrderHistory(user)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private OrderResponseDto convertToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderDate(order.getOrderDate().toString());
        orderResponseDto.setTickets(order.getTickets()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }

    private TicketDto convertToDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setCinemaHallId(ticket.getCinemaHall().getId());
        ticketDto.setMovieId(ticket.getMovie().getId());
        ticketDto.setShowTime(ticket.getShowTime().toString());
        ticketDto.setTicketId(ticket.getId());
        ticketDto.setUserEmail(ticket.getUser().getEmail());
        return ticketDto;
    }
}
