package dev.cinema.controllers;

import dev.cinema.models.MovieSession;
import dev.cinema.models.dto.MovieSessionRequestDto;
import dev.cinema.models.dto.MovieSessionResponseDto;
import dev.cinema.service.CinemaHallService;
import dev.cinema.service.MovieService;
import dev.cinema.service.MovieSessionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private MovieSessionService movieSessionService;
    private CinemaHallService cinemaHallService;
    private MovieService movieService;

    public MovieSessionController(MovieSessionService movieSessionService,
                                  CinemaHallService cinemaHallService, MovieService movieService) {
        this.movieSessionService = movieSessionService;
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    @PostMapping
    public void addMovieSession(@RequestBody @Valid MovieSessionRequestDto movieSessionRequestDto) {
        movieSessionService.add(convertFromDto(movieSessionRequestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllAvailableMovieSessions(@RequestParam
                                                                          @NotNull Long movieId,
                                                                      @RequestParam
                                                                      @NotNull String date) {
        return movieSessionService.findAvailableSessions(movieId,
                LocalDate.parse(date, dateTimeFormatter))
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private MovieSessionResponseDto convertToDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime().toString());
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        return movieSessionResponseDto;
    }

    private MovieSession convertFromDto(MovieSessionRequestDto movieSessionDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionDto.getCinemaHallId()));
        movieSession.setMovie(movieService.get(movieSessionDto.getMovieId()));
        movieSession.setShowTime(LocalDateTime.parse(movieSessionDto.getShowTime()));
        return movieSession;
    }
}
