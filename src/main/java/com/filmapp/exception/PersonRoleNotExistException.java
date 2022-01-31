package com.filmapp.exception;

import com.filmapp.commons.exception.NotExistException;

public class PersonRoleNotExistException extends NotExistException {

    private static final String PERSON_ROLE_NOT_EXIST = "Person role does not exist";

    public PersonRoleNotExistException() {
        super(PERSON_ROLE_NOT_EXIST);
    }
}
