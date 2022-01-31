package com.filmapp.person.exception;

import com.filmapp.commons.exception.NotExistException;

public class PersonNotExistsException extends NotExistException {

    private static final String PERSON_NOT_EXIST = "Person does not exist";

    public PersonNotExistsException() {
        super(PERSON_NOT_EXIST);
    }
}
