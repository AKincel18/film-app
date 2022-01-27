package com.filmapp.exception;

public class CategoryNotExistException extends Exception {

    private static final String CATEGORY_NOT_EXIST = "Category does not exist";

    public CategoryNotExistException() {
        super(CATEGORY_NOT_EXIST);
    }
}
