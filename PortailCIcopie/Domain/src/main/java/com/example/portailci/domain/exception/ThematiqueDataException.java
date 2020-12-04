package com.example.portailci.domain.exception;

public class ThematiqueDataException extends ExceptionGeneric {
    public ThematiqueDataException(String message) {
        super(ExceptionGeneric.COMPULSORY_DATA + message, ExceptionGeneric.COMPULSORY_DATA);
    }
}
