package dev.cinema.controllers;

import dev.cinema.models.CinemaHall;
import dev.cinema.models.dto.CinemaHallRequestDto;
import dev.cinema.models.dto.CinemaHallResponseDto;
import dev.cinema.service.CinemaHallService;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private CinemaHallService cinemaHallService;

    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallDto) {
        cinemaHallService.add(convertFromDto(cinemaHallDto));
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private CinemaHallResponseDto convertToDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setCinemaHallId(cinemaHall.getId());
        return cinemaHallResponseDto;
    }

    private CinemaHall convertFromDto(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }
}
