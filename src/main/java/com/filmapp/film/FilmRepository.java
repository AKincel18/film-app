package com.filmapp.film;

import com.filmapp.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
    List<Film> findFilmsByCategory(Category category);
    List<Film> findFilmsByReleaseDateBetween(LocalDate dateFrom, LocalDate dateTo);
}
