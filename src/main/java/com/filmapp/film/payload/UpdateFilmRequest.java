package com.filmapp.film.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UpdateFilmRequest {
    @NotNull(message = "Film id must be given")
    private Long id;
    private String name;
    private Long categoryId;
    private Long directorId;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
}
