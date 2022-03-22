package com.mutantes.mutantesapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * RuntimeException para NO-MUTANTES
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class NonMutantException extends RuntimeException {

    public NonMutantException(String message) { super(message);}
}
