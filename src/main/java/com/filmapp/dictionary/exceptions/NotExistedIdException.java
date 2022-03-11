package com.filmapp.dictionary.exceptions;

public class NotExistedIdException extends Exception {

    private static final String NOT_EXISTED_ID_MESSAGE = "Id does not exist";

    public NotExistedIdException() {
        super(NOT_EXISTED_ID_MESSAGE);
    }
}
