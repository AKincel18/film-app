package com.filmapp.film;

import com.filmapp.category.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public List<Film> findFilmsByCategory(Category category /*dto*/) {
        return filmRepository.findFilmsByCategory(category);
    }

    public Film createFilm(Film film) {
        return filmRepository.save(film);
    }
}
