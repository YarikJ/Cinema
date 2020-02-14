package dev.cinema.service.impl;

import dev.cinema.dao.CinemaHallDao;
import dev.cinema.lib.Inject;
import dev.cinema.lib.Service;
import dev.cinema.models.CinemaHall;
import dev.cinema.service.CinemaHallService;

import java.util.List;

@Service
public class CinemaHallServiceImpl implements CinemaHallService {
    @Inject
    private static CinemaHallDao cinemaHallDao;

    @Override
    public CinemaHall add(CinemaHall cinemaHall) {
        return cinemaHallDao.add(cinemaHall);
    }

    @Override
    public List<CinemaHall> getAll() {
        return cinemaHallDao.getAll();
    }
}
