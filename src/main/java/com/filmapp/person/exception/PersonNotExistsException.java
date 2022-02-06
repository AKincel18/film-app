package com.filmapp.person.exception;

import com.filmapp.commons.exception.NotExistException;

public class PersonNotExistsException extends NotExistException {

    public PersonNotExistsException() {
        super(String.format(NOT_EXIST_MSG, "Person"));
    }
}
