package com.filmapp.film;

import com.filmapp.category.Category;
import com.filmapp.person.Person;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Document("films")
public class Film {
    @Id
    private ObjectId id;
    private String name;
    private Person director;
    private LocalDate releaseDate;
    private Integer runningTime;
    private Double budget;
    @DBRef
    private List<Person> actors;
    @DBRef
    private Category category;
}
