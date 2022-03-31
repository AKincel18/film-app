package com.filmapp.dictionary.exceptions;

import com.filmapp.commons.exception.NotExistException;

public class DictionaryNotExistException extends NotExistException {

    public DictionaryNotExistException(String message) {
        super(String.format(NOT_EXIST_MSG, message));
    }
}
