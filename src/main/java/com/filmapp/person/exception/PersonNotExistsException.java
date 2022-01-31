package com.filmapp.person.exception;

public class PersonNotExistsException extends Exception {

    private static final String PERSON_NOT_EXIST = "Person does not exist";

    public PersonNotExistsException() {
        super(PERSON_NOT_EXIST);
    }
}
