package dev.cinema.service;

import dev.cinema.models.Movie;
import java.util.List;

public interface MovieService {

    Movie add(Movie movie);

    List<Movie> getAll();
}
