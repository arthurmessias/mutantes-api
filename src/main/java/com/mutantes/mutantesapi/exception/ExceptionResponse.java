package com.mutantes.mutantesapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

/**
 * Exception POJO Customizado
 */
@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
}
