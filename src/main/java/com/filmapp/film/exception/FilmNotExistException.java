package com.filmapp.film.exception;

import com.filmapp.commons.exception.NotExistException;

public class FilmNotExistException extends NotExistException {

    public FilmNotExistException() {
        super(String.format(NOT_EXIST_MSG, "Film"));
    }
}
