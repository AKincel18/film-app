package com.filmapp.category.exception;

import com.filmapp.commons.exception.NotExistException;

public class CategoryNotExistException extends NotExistException {

    public CategoryNotExistException() {
        super(String.format(NOT_EXIST_MSG, "Category"));
    }
}
