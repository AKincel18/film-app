package com.filmapp.commons.exception;

public class CannotAddException extends Exception {
    protected static final String CANNOT_ADD_MSG = "Cannot add %s, contact with administrator";

    public CannotAddException(String message) {
        super(message);
    }
}
