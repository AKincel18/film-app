package com.filmapp.commons.exception.processing;

import com.filmapp.commons.exception.NotExistException;
import com.filmapp.commons.response.MessageResponse;
import com.filmapp.dictionary.exceptions.DuplicatedDictionaryNameException;
import com.filmapp.dictionary.exceptions.NotExistedIdException;
import com.filmapp.dictionary.exceptions.NotProvidedIdException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = MyExceptionProcessing.class)
public class MyExceptionControllerAdvice {

    @ExceptionHandler({
            NotExistException.class,
            DuplicatedDictionaryNameException.class,
            NotProvidedIdException.class,
            NotExistedIdException.class
    })
    ResponseEntity<?> handleMyExceptions(Exception e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
}
