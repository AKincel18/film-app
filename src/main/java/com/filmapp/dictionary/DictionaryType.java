package com.filmapp.dictionary;

import lombok.Getter;

public enum DictionaryType {
    CATEGORY("Category"),
    PERSON_ROLE("Person role"),
    USER_ROLE("User role");

    @Getter
    private final String name;

    DictionaryType(String name) {
        this.name = name;
    }
}
