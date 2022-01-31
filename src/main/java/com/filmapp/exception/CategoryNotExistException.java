package com.filmapp.exception;

import com.filmapp.commons.exception.NotExistException;

public class CategoryNotExistException extends NotExistException {

    private static final String CATEGORY_NOT_EXIST = "Category does not exist";

    public CategoryNotExistException() {
        super(CATEGORY_NOT_EXIST);
    }
}
