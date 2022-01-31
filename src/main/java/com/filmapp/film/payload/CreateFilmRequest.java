package com.filmapp.film.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateFilmRequest {
    @NotNull
    private String name;
    @NotNull
    private Long categoryId;
    @NotNull
    private Long directorId;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
}
