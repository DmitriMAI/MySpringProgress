package com.example.demo.exception;

public class PersonExistingEmailException extends RuntimeException{
    public PersonExistingEmailException(String message){
        super(message);
    }
    public PersonExistingEmailException(String message, Throwable cause){
        super(message, cause);
    }
}
