package com.filmapp.role.user;

import com.filmapp.role.user.exception.CannotAddUserRoleException;

public enum UserRoleEnum {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_MODERATOR;

    public static UserRoleEnum findUserRoleEnum(String name) throws CannotAddUserRoleException {
        UserRoleEnum userRoleEnum;
        try {
            userRoleEnum = valueOf(name);
        } catch (IllegalArgumentException e) {
            throw new CannotAddUserRoleException();
        }
        return userRoleEnum;
    }
}
