package com.filmapp.role.person.exception;

import com.filmapp.commons.exception.CannotAddException;

public class CannotAddPersonRoleException extends CannotAddException {

    public CannotAddPersonRoleException() {
        super(String.format(CANNOT_ADD_MSG, "person role"));
    }
}
