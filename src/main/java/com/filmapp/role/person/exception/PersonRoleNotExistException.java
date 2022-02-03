package com.filmapp.role.person.exception;

import com.filmapp.commons.exception.NotExistException;

public class PersonRoleNotExistException extends NotExistException {

    public PersonRoleNotExistException() {
        super(String.format(NOT_EXIST_MSG, "Person role"));
    }
}
