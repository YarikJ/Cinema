package dev.cinema;

import dev.cinema.lib.Injector;
import dev.cinema.models.Movie;
import dev.cinema.service.MovieService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    private static Injector injector = Injector.getInstance("dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Forrest Gump");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
