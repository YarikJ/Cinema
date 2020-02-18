package dev.cinema;

import dev.cinema.lib.Injector;
import dev.cinema.models.CinemaHall;
import dev.cinema.models.Movie;
import dev.cinema.models.MovieSession;
import dev.cinema.models.ShoppingCart;
import dev.cinema.models.User;
import dev.cinema.service.AuthenticationService;
import dev.cinema.service.CinemaHallService;
import dev.cinema.service.MovieService;
import dev.cinema.service.MovieSessionService;
import dev.cinema.service.OrderService;
import dev.cinema.service.ShoppingCartService;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("dev.cinema");

    public static void main(String[] args) {

        // create movies
        Movie movie1 = new Movie();
        movie1.setTitle("Forrest Gump");
        movie1.setDescription("Drama, comedy");

        Movie movie2 = new Movie();
        movie2.setTitle("Howl's Moving Castle");
        movie2.setDescription("Animated fantasy film");

        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie1);
        movieService.add(movie2);
        movieService.getAll().forEach(System.out::println);

        //create cinema halls
        CinemaHall redHall = new CinemaHall();
        redHall.setCapacity(100);
        redHall.setDescription("Red Hall");

        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(50);
        blueHall.setDescription("Blue Hall");

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(redHall);
        cinemaHallService.add(blueHall);
        cinemaHallService.getAll().forEach(System.out::println);

        //create movie sessions
        MovieSession movieSession1 = new MovieSession();
        movieSession1.setMovie(movie1);
        movieSession1.setShowTime(LocalDateTime.of(2020, 2,
                15, 20, 0));
        movieSession1.setCinemaHall(redHall);

        MovieSession movieSession2 = new MovieSession();
        movieSession2.setMovie(movie2);
        movieSession2.setShowTime(LocalDateTime.of(2020, 2,
                15, 19, 0));
        movieSession2.setCinemaHall(blueHall);

        MovieSessionService movieSessionService
                = (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession1);
        movieSessionService.add(movieSession2);
        System.out.println(movieSessionService.findAvailableSessions(1L, LocalDate.now()));
        System.out.println(movieSessionService.findAvailableSessions(2L, LocalDate.now()));

        // create user
        AuthenticationService authenticationService
                = (AuthenticationService) injector.getInstance(AuthenticationService.class);
        authenticationService.register("me@gmail.com", "111");
        authenticationService.register("you@gmail.com", "111");

        System.out.println(authenticationService
                .register("first@gmail.com", "111"));

        // add ticket and register shopping cart
        ShoppingCartService shoppingCartService =
                (ShoppingCartService) injector.getInstance(ShoppingCartService.class);
        User user = authenticationService.login("first@gmail.com", "111");
        shoppingCartService.registerNewShoppingCart(user);

        shoppingCartService.addSession(movieSession1, user);
        System.out.println(shoppingCartService.getByUser(user));

        shoppingCartService.addSession(movieSession2, user);

        User user1 = authenticationService.login("you@gmail.com", "111");
        shoppingCartService.registerNewShoppingCart(user1);

        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);

        // complete order
        OrderService orderService = (OrderService) injector.getInstance(OrderService.class);
        ShoppingCart shoppingCart1 = shoppingCartService.getByUser(user1);
        orderService.completeOrder(shoppingCart1.getTickets(), user1);
        System.out.println(orderService.getOrderHistory(user1));

        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);
        shoppingCartService.addSession(movieSession1, user1);

        ShoppingCart shoppingCart2 = shoppingCartService.getByUser(user1);
        orderService.completeOrder(shoppingCart2.getTickets(), user1);
        System.out.println(orderService.getOrderHistory(user1));
    }
}
