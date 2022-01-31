package com.filmapp.film;

import com.filmapp.category.Category;
import com.filmapp.category.CategoryRepository;
import com.filmapp.exception.CategoryNotExistException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
public class FilmService {

    private final FilmRepository filmRepository;
    private final CategoryRepository categoryRepository;
    private final FilmMapper mapper;

    public FilmService(FilmRepository filmRepository, CategoryRepository categoryRepository) {
        this.filmRepository = filmRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = new FilmMapper();
    }

    //for testing todo - remove
    public List<FilmDto> getAllFilms() {
        return filmRepository.findAll()
                .stream()
                .map(f -> mapper.map(f, FilmDto.class))
                .collect(Collectors.toList());
    }

    public FilmDto createFilm(FilmDto filmDto) throws CategoryNotExistException {
        Category category = null;//categoryRepository.getByName(filmDto.getCategory());
        if (category == null)
            throw new CategoryNotExistException();
        Film film = filmRepository.save(mapper.map(category, filmDto, Film.class));
        return mapper.map(film, FilmDto.class);
    }

    public FilmDto findFilmById(Long id) {
        Optional<Film> film = filmRepository.findById(id);
        return film.map(f -> mapper.map(f, FilmDto.class)).orElse(null);
    }

    public List<FilmDto> findFilmsByCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(c -> filmRepository.findFilmsByCategory(c)
                        .stream()
                        .map(f -> mapper.map(f, FilmDto.class))
                        .collect(Collectors.toList()))
                .orElse(null);
    }

    public List<FilmDto> findFilmsByCurrentMonth() {
        int year = LocalDate.now().getYear();
        int month = LocalDate.now().getMonthValue();
        return findFilmsByMonth(year, month);
    }

    public List<FilmDto> findFilmsByMonth(int year, int month) {
        LocalDate dateFrom = LocalDate.of(year, month, 1);
        LocalDate dateTo = LocalDate.of(year, month, 1).with(lastDayOfMonth());
        return findFilmsByReleaseDateBetween(dateFrom, dateTo);
    }

    public List<FilmDto> findFilmsByDates(LocalDate dateFrom, LocalDate dateTo) {
        return findFilmsByReleaseDateBetween(dateFrom, dateTo);
    }

    private List<FilmDto> findFilmsByReleaseDateBetween(LocalDate dateFrom, LocalDate dateTo) {
        return filmRepository.findFilmsByReleaseDateBetween(dateFrom, dateTo)
                .stream()
                .map(f -> mapper.map(f, FilmDto.class))
                .collect(Collectors.toList());
    }

}
