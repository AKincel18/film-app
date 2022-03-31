package com.filmapp.category.exceptions;

import com.filmapp.commons.exception.NotExistException;

public class CategoryNotExistException extends NotExistException {

    public CategoryNotExistException() {
        super(String.format(NOT_EXIST_MSG, "Category"));
    }
}
