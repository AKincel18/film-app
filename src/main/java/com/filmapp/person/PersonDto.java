package com.filmapp.person;

import com.filmapp.film.Film;
import com.filmapp.role.person.PersonRoleDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class PersonDto {

    private Long id;

    private String firstName;

    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    private PersonRoleDto personRole;

    private List<Film> films;
}
