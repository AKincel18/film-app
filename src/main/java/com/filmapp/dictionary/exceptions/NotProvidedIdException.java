package com.filmapp.dictionary.exceptions;

public class NotProvidedIdException extends Exception {

    private static final String NOT_PROVIDED_ID_MESSAGE = "Id not provided";

    public NotProvidedIdException() {
        super(NOT_PROVIDED_ID_MESSAGE);
    }
}
