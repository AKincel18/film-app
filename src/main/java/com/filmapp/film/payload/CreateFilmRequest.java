package com.filmapp.film.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreateFilmRequest {
    @NotBlank(message = "Name must be not blank")
    private String name;
    @NotNull(message = "Category must be given")
    private Long categoryId;
    @NotNull(message = "Director must be given")
    private Long directorId;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
}
