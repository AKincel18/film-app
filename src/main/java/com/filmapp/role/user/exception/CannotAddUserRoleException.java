package com.filmapp.role.user.exception;

import com.filmapp.commons.exception.CannotAddException;

public class CannotAddUserRoleException extends CannotAddException {

    public CannotAddUserRoleException() {
        super(String.format(CANNOT_ADD_MSG, "user role"));
    }
}
