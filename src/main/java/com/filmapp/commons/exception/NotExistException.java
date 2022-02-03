package com.filmapp.commons.exception;

public class NotExistException extends Exception {

    protected static final String NOT_EXIST_MSG = "%s does not exist";

    public NotExistException(String message) {
        super(message);
    }
}
