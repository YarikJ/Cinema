package dev.cinema.service.impl;

import dev.cinema.dao.MovieDao;
import dev.cinema.models.Movie;
import dev.cinema.service.MovieService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    public Movie add(Movie movie) {
        return movieDao.add(movie);
    }

    @Override
    public Movie get(Long movieId) {
        return movieDao.get(movieId);
    }

    @Override
    public List<Movie> getAll() {
        return movieDao.getAll();
    }
}
