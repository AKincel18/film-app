package com.filmapp.person;

import com.filmapp.film.Film;
import com.filmapp.role.person.PersonRole;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    private String lastName;

    @Column(name = "birth_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    //private String nationality;

//    @OneToOne(mappedBy = "film_id")
//    private Film film;

    @OneToOne
    @JoinColumn(name = "person_role_id", referencedColumnName = "id")
    private PersonRole role;

    @ManyToMany(mappedBy = "actors")
    private Set<Film> films;

}
