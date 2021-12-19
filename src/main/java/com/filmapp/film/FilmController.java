package com.filmapp.film;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/films")
public class FilmController {

    private final FilmService filmService;

    @GetMapping
    ResponseEntity<List<Film>> findAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @PostMapping
    ResponseEntity<Film> createFilm(Film filmToCreate) {
        Film createdFilm = filmService.createFilm(filmToCreate);
        return ResponseEntity.created(URI.create("/" + createdFilm.getId())).body(createdFilm);
    }
}
