package dev.cinema.service.impl;

import dev.cinema.dao.ShoppingCartDao;
import dev.cinema.dao.TicketDao;
import dev.cinema.models.MovieSession;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.Ticket;
import dev.cinema.models.User;
import dev.cinema.service.ShoppingCartService;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

    @Override
    public void addSession(MovieSession movieSession, User user) {
        Ticket ticket = new Ticket();
        ticket.setCinemaHall(movieSession.getCinemaHall());
        ticket.setMovie(movieSession.getMovie());
        ticket.setShowTime(movieSession.getShowTime());
        ticket.setUser(user);

        ShoppingCart shoppingCart = getByUser(user);
        shoppingCart.getTickets().add(ticketDao.add(ticket));
        shoppingCartDao.update(shoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCart.setTickets(new ArrayList<>());
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCartDao.clear(shoppingCart);
    }
}
