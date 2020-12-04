package com.example.portailci.domain.exception;

public class AlreadyExistsException extends ExceptionGeneric {
    public AlreadyExistsException(String message) {
        super(ExceptionGeneric.ALREADY_EXISTS + message, ExceptionGeneric.ALREADY_EXISTS);
    }
}
