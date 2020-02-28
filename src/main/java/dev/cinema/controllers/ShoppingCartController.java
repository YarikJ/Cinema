package dev.cinema.controllers;

import dev.cinema.models.MovieSession;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.Ticket;
import dev.cinema.models.User;
import dev.cinema.models.dto.MovieSessionRequestDto;
import dev.cinema.models.dto.ShoppingCartRequestDto;
import dev.cinema.models.dto.TicketDto;
import dev.cinema.service.CinemaHallService;
import dev.cinema.service.MovieService;
import dev.cinema.service.ShoppingCartService;
import dev.cinema.service.UserService;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private ShoppingCartService shoppingCartService;
    private UserService userService;
    private CinemaHallService cinemaHallService;
    private MovieService movieService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService,
                                  CinemaHallService cinemaHallService, MovieService movieService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @PostMapping("/add-movie-session")
    public void addMovieSession(@RequestBody MovieSessionRequestDto movieSessionRequestDto,
                                @RequestParam Long userId) {
        User user = userService.get(userId);
        shoppingCartService.addSession(convertFromDto(movieSessionRequestDto), user);
    }

    @GetMapping("/by-user")
    public ShoppingCartRequestDto getByUser(@RequestParam Long userId) {
        User user = userService.get(userId);
        return convertToDto(shoppingCartService.getByUser(user));
    }

    private ShoppingCartRequestDto convertToDto(ShoppingCart shoppingCart) {
        ShoppingCartRequestDto shoppingCartDto = new ShoppingCartRequestDto();
        shoppingCartDto.setUserEmail(shoppingCart.getUser().getEmail());
        shoppingCartDto.setDtoTickets(shoppingCart.getTickets()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
        return shoppingCartDto;
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

    private MovieSession convertFromDto(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionRequestDto.getShowTime()));
        return movieSession;
    }
}
