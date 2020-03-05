package dev.cinema.models.dto;

import javax.validation.constraints.NotNull;

public class MovieRequestDto {
    @NotNull(message = "provide movie title")
    private String title;
    @NotNull(message = "Please provide movie description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
