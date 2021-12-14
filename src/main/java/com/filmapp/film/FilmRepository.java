package com.filmapp.film;

import com.filmapp.category.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends MongoRepository<Film, ObjectId> {
    List<Film> findFilmsByCategory(Category category);
}
