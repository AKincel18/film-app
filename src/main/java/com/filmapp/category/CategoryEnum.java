package com.filmapp.category;

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
}
