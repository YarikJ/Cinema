package dev.cinema.models.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

public class CinemaHallRequestDto {
    @NotNull
    @Max(value = 200)
    private int capacity;
    @NotNull
    private String description;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
