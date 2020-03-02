package dev.cinema.service;

import dev.cinema.models.Movie;
import java.util.List;

public interface MovieService {

    Movie add(Movie movie);

    Movie get(Long movieId);

    List<Movie> getAll();
}
