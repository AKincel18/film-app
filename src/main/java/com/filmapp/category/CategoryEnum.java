package com.filmapp.category;

import com.filmapp.category.exception.CannotAddCategoryException;

import java.util.Arrays;

public enum CategoryEnum {
    COMEDY("Comedy"),
    THRILLER("Thriller"),
    FANTASY("Fantasy"),
    ACTION("Action"),
    ROMANCE("Romance"),
    WESTERN("Western");

    private final String name;

    CategoryEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static CategoryEnum findCategoryEnum(String name) throws CannotAddCategoryException {
        return Arrays.stream(values())
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(CannotAddCategoryException::new);
    }
}
