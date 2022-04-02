package com.filmapp.film;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.commons.exception.processing.MyExceptionProcessing;
import com.filmapp.commons.pagination.PaginationResponseEntity;
import com.filmapp.commons.pagination.PaginationResult;
import com.filmapp.film.exception.FilmNotExistException;
import com.filmapp.film.payload.CreateFilmRequest;
import com.filmapp.film.payload.UpdateFilmRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@MyExceptionProcessing
@RequestMapping("api/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    ResponseEntity<List<FilmDto>> getPaginatedFilms(@RequestParam("pageSize") int pageSize,
                                                    @RequestParam("pageIndex") int pageIndex) {
        PaginationResult<FilmDto> result = filmService.getPaginatedFilms(pageSize, pageIndex);
        return PaginationResponseEntity.ok(result);
    }

    @PostMapping
    ResponseEntity<?> createFilm(@Valid @RequestBody CreateFilmRequest request) throws NotExistException {
        FilmDto createdFilmDto = filmService.createFilm(request);
        return ResponseEntity.created(URI.create("/" + createdFilmDto.getId())).body(createdFilmDto);
    }

    @PatchMapping
    ResponseEntity<?> updateFilm(@RequestBody @Valid UpdateFilmRequest request) throws NotExistException {
        FilmDto updatedFilm = filmService.updateFilm(request);
        return ResponseEntity.ok(updatedFilm);
    }

    @GetMapping("/{id}")
    ResponseEntity<?> findFilmById(@PathVariable Long id) throws FilmNotExistException {
        FilmDto filmDto = filmService.findFilmDtoById(id);
        return ResponseEntity.ok(filmDto);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<?> findFilmsByCategory(@PathVariable("id") Long categoryId) throws NotExistException {
        List<FilmDto> films = filmService.findFilmsByCategory(categoryId);
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
    ResponseEntity<?> deleteFilm(@PathVariable Long id) throws FilmNotExistException {
        filmService.deleteFilm(id);
        return ResponseEntity.ok().build();
    }
}
