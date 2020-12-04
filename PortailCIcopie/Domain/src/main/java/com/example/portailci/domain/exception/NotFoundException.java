package com.example.portailci.domain.exception;

public class NotFoundException extends ExceptionGeneric {
    public NotFoundException(String message) {
        super(ExceptionGeneric.NOT_FOUND+message, ExceptionGeneric.NOT_FOUND);
    }
}

