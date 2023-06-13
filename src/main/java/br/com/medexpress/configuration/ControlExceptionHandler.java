package br.com.medexpress.configuration;

import br.com.medexpress.exceptions.ApiException;
import br.com.medexpress.exceptions.BadRequestException;
import br.com.medexpress.exceptions.InternalServerErrorException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;
import java.util.Optional;

@ControllerAdvice // -> Só entra aqui quando a requisição nao tem sucesso ou tem algum problema no fluxo;
public class ControlExceptionHandler {

    //as mensagens de erro vem dessa classe do tipo ResponseEntity
    @ExceptionHandler({ConstraintViolationException.class}) //constraint são os erros gerados pelos @'s
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException) {
        final var errors = constraintViolationException.getConstraintViolations().stream()
                .map(violation -> violation.getRootBeanClass().getName() + " "
                    + violation.getPropertyPath() + ": "
                    + violation.getMessage());
        return ResponseEntity.status(400).body(errors);
    }

    //Exceção customizada
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException exception) {
        final var body = exception.getBody();
        return ResponseEntity.status(exception.getStatus()).body(body);
    }

    //Qualquer exceção a ser disparada que não esteja inclusa em nenhuma das outras
    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Object> handleException(Throwable throwable) {
        final var exception =  InternalServerErrorException.builder()
                .message("Oh oh- something's wrong")
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

    //Quando o tipo do parâmetro não condiz com o tipo da classe do backend
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleException(MethodArgumentTypeMismatchException exceptionMethod) {
    final var message = "Error processing: " + exceptionMethod.getName() + " should be " + exceptionMethod
            .getRequiredType()
            .getName();
        final var exception =  BadRequestException.builder()
                .message(message)
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

    //Quando algum parâmetro não é valido;
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<Object> handleException(MethodArgumentNotValidException exceptionMethod) {
        final var fieldErrors = exceptionMethod.getBindingResult().getFieldErrors();
        final var fieldErrorDtos = fieldErrors.stream()
                .map(f -> f.getField()
                        .concat(":")
                        .concat(Objects.requireNonNull(f.getDefaultMessage())))
                .map(String::new)
                .toList();
        final var message = "Field Errors: " + fieldErrorDtos;
        final var exception =  BadRequestException.builder()
                .message(message)
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

    //Quando o backend não consegue processar o body ou o header da requisição, mostrará o motivo.
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleException(HttpMessageNotReadableException exceptionMethod) {
        final var mostSpecificCause = exceptionMethod.getMostSpecificCause();
        final var message = "Error: " + mostSpecificCause.getMessage();
        final var exception =  BadRequestException.builder()
                .message(message)
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

    //Erro por falta de informações/metadados na requisição.
    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleException(MissingServletRequestParameterException exceptionMethod) {
        final var exception =  BadRequestException.builder()
                .message(Optional.of(exceptionMethod.getMessage()).orElse(exceptionMethod.toString()))
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

    //Uso do verbo errado; Ex: GET onde é POST. etc
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleException(HttpRequestMethodNotSupportedException exceptionMethod) {
        final var exception =  BadRequestException.builder()
                .message(Optional.of(exceptionMethod.getMessage()).orElse(exceptionMethod.toString()))
                .build();
        return ResponseEntity.status(exception.getStatus()).body(exception.getBody());
    }

}
