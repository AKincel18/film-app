package com.filmapp.person;

import com.filmapp.film.Film;
import com.filmapp.role.person.RolePerson;
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
@Document
public class Person {
    @Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    //private String nationality;
    @DBRef
    private RolePerson rolePerson;
    @DBRef
    private List<Film> films;

}
