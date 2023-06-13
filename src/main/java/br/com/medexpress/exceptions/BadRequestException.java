package br.com.medexpress.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException{

    @Builder
    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
