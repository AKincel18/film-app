package com.filmapp.category.exception;

import com.filmapp.commons.exception.DuplicatedException;

public class DuplicatedCategoryException extends DuplicatedException {

    public DuplicatedCategoryException() {
        super(String.format(DUPLICATES_MSG, "Category"));
    }
}
