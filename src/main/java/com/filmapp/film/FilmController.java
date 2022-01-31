package com.filmapp.film;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.exception.CategoryNotExistException;
import com.filmapp.film.exception.FilmNotExistException;
import com.filmapp.film.payload.CreateFilmRequest;
import com.filmapp.film.payload.UpdateFilmRequest;
import com.filmapp.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping
    ResponseEntity<List<FilmDto>> findAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @PostMapping
    ResponseEntity<?> createFilm(@RequestBody @Valid CreateFilmRequest request) {
        FilmDto createdFilmDto;
        try {
            createdFilmDto = filmService.createFilm(request);
        } catch (NotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + createdFilmDto.getId())).body(createdFilmDto);
    }

    @PatchMapping
    ResponseEntity<?> updateFilm(@RequestBody UpdateFilmRequest request) {
        FilmDto updatedFilm;
        try {
            updatedFilm = filmService.updateFilm(request);
        } catch (NotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(updatedFilm);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findFilmById(@PathVariable Long id) {
        FilmDto filmDto;
        try {
            filmDto = filmService.findFilmDtoById(id);
        } catch (FilmNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok(filmDto);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<?> findFilmsByCategory(@PathVariable("id") Long categoryId) {
        List<FilmDto> films;
        try {
            films = filmService.findFilmsByCategory(categoryId);
        } catch (CategoryNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.ok().body(films);
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
