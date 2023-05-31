package br.com.medexpress.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = false) //invoca o construtor do runtimeException
@Data
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ExceptionBody getBody() {
        return ExceptionBody.builder()
                .status(this.status.value())
                .message(this.message)
                .build();

    }
}
//DTO
@Data
@Builder
@AllArgsConstructor
class ExceptionBody {
    private Integer status;
    private String message;
}

