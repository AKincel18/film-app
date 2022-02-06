package com.filmapp.role.person;

import com.filmapp.role.person.exception.CannotAddPersonRoleException;

import java.util.Arrays;

public enum PersonRoleEnum {
    ACTOR("Actor"),
    DIRECTOR("Director");

    private final String name;

    PersonRoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PersonRoleEnum findPersonRoleEnum(String name) throws CannotAddPersonRoleException {
        return Arrays.stream(values())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(CannotAddPersonRoleException::new);
    }
}
