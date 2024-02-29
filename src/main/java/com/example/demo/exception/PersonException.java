package com.example.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Data
public class PersonException {
    private final String message;
    private final Throwable throwable;
    private HttpStatus httpStatus;
}
