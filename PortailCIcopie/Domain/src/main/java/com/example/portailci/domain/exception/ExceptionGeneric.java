package com.example.portailci.domain.exception;

public abstract class ExceptionGeneric extends RuntimeException {
    public static final String NOT_FOUND = "GENERIC_NOT_FOUND";
    public static final String ALREADY_EXISTS = "GENERIC_ALREADY_EXISTS";
    public static final String THEMATIQUE_IS_NOT_EMPTY = "GENERIC_THEMATIQUE_NOT_EMPTY";
    public static final String COMPULSORY_DATA = "COMPULSORY_DATA";

    private final String code;

    public ExceptionGeneric(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
