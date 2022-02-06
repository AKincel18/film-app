package com.filmapp.role.person.exception;

import com.filmapp.commons.exception.DuplicatedException;

public class DuplicatedPersonRoleException extends DuplicatedException {

    public DuplicatedPersonRoleException() {
        super(String.format(DUPLICATES_MSG, "Person role"));
    }
}
