package com.mutantes.mutantesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * ExceptionHandler customizado para la API
 */
@ControllerAdvice
@RestController
public class MutantEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * STATUS 403 - NO-MUTANTE
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NonMutantException.class)
    public final ResponseEntity<ExceptionResponse> handleNotMutantExceptions(NonMutantException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * STATUS 500
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
