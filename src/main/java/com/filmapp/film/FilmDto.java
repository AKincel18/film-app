package com.filmapp.film;

import com.filmapp.category.CategoryDto;
import com.filmapp.person.PersonDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
public class FilmDto {
    private Long id;
    private String name;
    private PersonDto director;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
    private Set<PersonDto> actors;
    private CategoryDto category;
}
