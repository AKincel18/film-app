package com.filmapp.commons.exception.processing;

import com.filmapp.commons.exception.CannotAddException;
import com.filmapp.commons.exception.DuplicatedException;
import com.filmapp.commons.exception.NotExistException;
import com.filmapp.commons.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = MyExceptionProcessing.class)
public class MyExceptionControllerAdvice {

    @ExceptionHandler({CannotAddException.class, NotExistException.class, DuplicatedException.class})
    ResponseEntity<?> handleMyExceptions(Exception e) {
        return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
    }
}
