package dev.cinema.service;

import dev.cinema.models.MovieSession;

import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {

    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession session);
}
