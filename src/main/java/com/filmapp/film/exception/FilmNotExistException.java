package com.filmapp.film.exception;

import com.filmapp.commons.exception.NotExistException;

public class FilmNotExistException extends NotExistException {

    private static final String FILM_NOT_EXIST = "Film does not exist";

    public FilmNotExistException() {
        super(FILM_NOT_EXIST);
    }
}
