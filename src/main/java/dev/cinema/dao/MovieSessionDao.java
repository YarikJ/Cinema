package dev.cinema.dao;

import dev.cinema.models.MovieSession;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionDao {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
