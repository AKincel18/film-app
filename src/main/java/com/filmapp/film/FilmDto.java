package com.filmapp.film;

import com.filmapp.person.PersonDto;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {

    private Long id;

    private String name;
    private PersonDto director;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
    private List<PersonDto> actors;
    private String category;
}
