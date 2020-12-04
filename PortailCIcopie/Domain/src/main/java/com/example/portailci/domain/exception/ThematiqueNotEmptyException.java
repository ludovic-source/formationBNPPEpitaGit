package com.example.portailci.domain.exception;

public class ThematiqueNotEmptyException extends ExceptionGeneric {
    public ThematiqueNotEmptyException(String message) {
        super(ExceptionGeneric.THEMATIQUE_IS_NOT_EMPTY + message, ExceptionGeneric.THEMATIQUE_IS_NOT_EMPTY);
    }
}
