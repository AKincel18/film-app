package com.filmapp.commons.exception;

public class DuplicatedException extends Exception {
    protected static final String DUPLICATES_MSG = "%s duplicated!";

    public DuplicatedException(String message) {
        super(message);
    }
}
