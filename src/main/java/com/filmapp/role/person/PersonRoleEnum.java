package com.filmapp.role.person;

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
}
