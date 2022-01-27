package com.filmapp.film;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.filmapp.person.PersonDto;
import lombok.Data;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Data
public class FilmDto {

    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;
    private PersonDto director;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
    private List<PersonDto> actors;
    private String category;
}
