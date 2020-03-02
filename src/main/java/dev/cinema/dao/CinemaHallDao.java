package dev.cinema.dao;

import dev.cinema.models.CinemaHall;

import java.util.List;

public interface CinemaHallDao {

    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    CinemaHall get(Long cinemaHallId);
}
