package com.filmapp.film;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    //for testing todo remove
    @GetMapping
    ResponseEntity<List<FilmDto>> findAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @PostMapping
    ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto filmToCreateDto) {
        FilmDto createdFilmDto = filmService.createFilm(filmToCreateDto);
        return ResponseEntity.created(URI.create("/" + createdFilmDto.getId())).body(createdFilmDto);
    }

    @GetMapping("/{id}")
    ResponseEntity<FilmDto> findFilmById(@PathVariable ObjectId id) {
        FilmDto filmDto = filmService.findFilmById(id);
        if (filmDto != null) {
            return ResponseEntity.ok(filmDto);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/category/{id}")
    ResponseEntity<List<FilmDto>> findFilmsByCategory(@PathVariable("id") ObjectId categoryId) {
        List<FilmDto> films = filmService.findFilmsByCategory(categoryId);
        if (films != null) {
            return ResponseEntity.ok(films);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/current-month")
    ResponseEntity<List<FilmDto>> findFilmsByCurrentMonth() {
        List<FilmDto> films = filmService.findFilmsByCurrentMonth();
        return ResponseEntity.ok(films);
    }

    @PostMapping("/month")
    ResponseEntity<List<FilmDto>> findFilmsByMonth(@RequestParam("year") int year,
                                                   @RequestParam("month") int month) {
        List<FilmDto> films = filmService.findFilmsByMonth(year, month);
        return ResponseEntity.ok(films);
    }

    @PostMapping("/dates")
    ResponseEntity<List<FilmDto>> findFilmsByDates(@RequestParam("dateFrom")LocalDate dateFrom,
                                                   @RequestParam("dateTo")LocalDate dateTo) {
        List<FilmDto> films = filmService.findFilmsByDates(dateFrom, dateTo);
        return ResponseEntity.ok(films);
    }
}
