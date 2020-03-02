package dev.cinema.service.impl;

import dev.cinema.dao.CinemaHallDao;
import dev.cinema.models.CinemaHall;
import dev.cinema.service.CinemaHallService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    private final CinemaHallDao cinemaHallDao;

    public CinemaHallServiceImpl(CinemaHallDao cinemaHallDao) {
        this.cinemaHallDao = cinemaHallDao;
    }

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public CinemaHall get(Long cinemaHallId) {
        return cinemaHallDao.get(cinemaHallId);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
