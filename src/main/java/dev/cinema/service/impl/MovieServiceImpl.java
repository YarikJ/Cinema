package dev.cinema.service.impl;

import dev.cinema.dao.MovieDao;
import dev.cinema.dao.impl.MovieDaoImpl;

import dev.cinema.lib.Inject;
import dev.cinema.models.Movie;
import dev.cinema.service.MovieService;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MovieServiceImpl implements MovieService {
    @Inject
    private MovieDao movieDao;
    private static final Logger LOGGER = LogManager.getLogger(MovieDaoImpl.class);

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
