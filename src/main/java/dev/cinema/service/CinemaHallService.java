package dev.cinema.service;

import dev.cinema.models.CinemaHall;

import java.util.List;

public interface CinemaHallService {

    CinemaHall add(CinemaHall cinemaHall);

    CinemaHall get(Long cinemaHallId);

    List<CinemaHall> getAll();
}
