package com.filmapp.film;

import com.filmapp.category.exception.CategoryNotExistException;
import com.filmapp.commons.exception.NotExistException;
import com.filmapp.film.exception.FilmNotExistException;
import com.filmapp.film.payload.CreateFilmRequest;
import com.filmapp.film.payload.UpdateFilmRequest;
import com.filmapp.response.MessageResponse;
import org.springframework.format.annotation.DateTimeFormat;
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
    ResponseEntity<?> createFilm(@Valid @RequestBody CreateFilmRequest request) {
        FilmDto createdFilmDto;
        try {
            createdFilmDto = filmService.createFilm(request);
        } catch (NotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return ResponseEntity.created(URI.create("/" + createdFilmDto.getId())).body(createdFilmDto);
    }

    @PatchMapping
    ResponseEntity<?> updateFilm(@RequestBody @Valid UpdateFilmRequest request) {
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

    @GetMapping("/month")
    ResponseEntity<List<FilmDto>> findFilmsByMonth(@RequestParam("year") int year,
                                                   @RequestParam("month") int month) {
        List<FilmDto> films = filmService.findFilmsByMonth(year, month);
        return ResponseEntity.ok(films);
    }

    @GetMapping("/dates")
    ResponseEntity<List<FilmDto>> findFilmsByDates(@RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
                                                   @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo) {
        List<FilmDto> films = filmService.findFilmsByDates(dateFrom, dateTo);
        return ResponseEntity.ok(films);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteFilm(@PathVariable Long id) {
        try {
            filmService.deleteFilm(id);
        } catch (FilmNotExistException e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
        return findAllFilms();
    }
}
