package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonExceptionHandler {
    @ExceptionHandler(value = PersonNotFoundException.class)
    public PersonException handleUserNotFoundException(PersonNotFoundException personNotFoundException) {
        return new PersonException(
                personNotFoundException.getMessage(),
                personNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(value = PersonExistingEmailException.class)
    public PersonException handleUserExistingEmailException(PersonExistingEmailException personExistingEmailException) {
        return new PersonException(
                personExistingEmailException.getMessage(),
                personExistingEmailException.getCause(),
                HttpStatus.CONFLICT
        );
    }
}
