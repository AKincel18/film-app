package com.filmapp.dictionary.exceptions;

public class DuplicatedDictionaryNameException extends Exception {

    private static final String DUPLICATED_MESSAGE = "%s already exists!";

    public DuplicatedDictionaryNameException(String name) {
        super(String.format(DUPLICATED_MESSAGE, name));
    }
}
