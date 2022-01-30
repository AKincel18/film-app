package com.filmapp.exception;

public class CannotAddCategoryException extends Exception {

    private static final String CANNOT_ADD_CATEGORY = "Cannot add category, contact with administrator";

    public CannotAddCategoryException() {
        super(CANNOT_ADD_CATEGORY);
    }
}
