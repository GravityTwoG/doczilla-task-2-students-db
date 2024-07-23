package org.gravitytwog.controllerAdvices;

import org.gravitytwog.exceptions.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class StudentsControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private ResponseEntity<ErrorMessage> handleEntityNotFound(
            EntityNotFoundException e,
            WebRequest request
    ) {

        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                LocalDate.now(),
                e.getMessage(),
                request.getDescription(false));

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(message);
    }
}

