package com.example.portailci.exposition.lien;

import com.example.portailci.application.lien.LienControleDonneesException;
import com.example.portailci.application.lien.LienNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class LienExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LienControleDonneesException.class)
    protected ResponseEntity<Object> bibliothequeNotFound(
            LienControleDonneesException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(LienNotFoundException.class)
    protected ResponseEntity<Object> bibliothequeNotFound(
            LienNotFoundException ex, WebRequest request) {
        String bodyOfResponse = ex.getMessage();
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}
