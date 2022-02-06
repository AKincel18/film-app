package com.filmapp.role.user.exception;

import com.filmapp.commons.exception.DuplicatedException;

public class DuplicatedUserRoleException extends DuplicatedException {

    public DuplicatedUserRoleException() {
        super(String.format(DUPLICATES_MSG, "User role"));
    }
}
