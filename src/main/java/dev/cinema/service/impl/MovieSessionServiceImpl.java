package dev.cinema.service.impl;

import dev.cinema.dao.MovieSessionDao;
import dev.cinema.lib.Inject;
import dev.cinema.lib.Service;
import dev.cinema.models.MovieSession;
import dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService {
    @Inject
    private MovieSessionDao movieSessionDao;

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        return movieSessionDao.findAvailableSessions(movieId, date);
    }

    @Override
    public MovieSession add(MovieSession session) {
        return movieSessionDao.add(session);
    }
}
