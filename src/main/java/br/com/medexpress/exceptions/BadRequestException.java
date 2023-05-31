package br.com.medexpress.exceptions;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}