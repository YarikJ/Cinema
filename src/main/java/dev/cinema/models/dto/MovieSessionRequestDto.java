package dev.cinema.models.dto;

import javax.validation.constraints.NotNull;

public class MovieSessionRequestDto {
    @NotNull(message = "provide show time")
    private String showTime;
    @NotNull(message = "provide cinema hall id")
    private Long cinemaHallId;
    @NotNull(message = "provide movie")
    private Long movieId;

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

}
