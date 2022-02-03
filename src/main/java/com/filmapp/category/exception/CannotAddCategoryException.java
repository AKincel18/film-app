package com.filmapp.category.exception;

import com.filmapp.commons.exception.CannotAddException;

public class CannotAddCategoryException extends CannotAddException {

    public CannotAddCategoryException() {
        super(String.format(CANNOT_ADD_MSG, "category"));
    }
}
