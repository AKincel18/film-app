package com.filmapp.exception;

public class PersonRoleNotExistException extends Exception {

    private static final String PERSON_ROLE_NOT_EXIST = "Person role does not exist";

    public PersonRoleNotExistException() {
        super(PERSON_ROLE_NOT_EXIST);
    }
}
