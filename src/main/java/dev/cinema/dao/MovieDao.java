package dev.cinema.dao;

import dev.cinema.models.Movie;

import java.util.List;

public interface MovieDao {

    Movie add(Movie movie);

    List<Movie> getAll();
}
