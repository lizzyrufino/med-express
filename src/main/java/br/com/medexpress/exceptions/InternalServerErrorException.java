package br.com.medexpress.exceptions;

import lombok.Builder;
import org.springframework.http.HttpStatus;

public class InternalServerErrorException extends ApiException{

    @Builder
    public InternalServerErrorException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message);
    }
}
