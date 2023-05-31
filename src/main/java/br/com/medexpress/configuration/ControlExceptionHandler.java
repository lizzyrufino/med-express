package br.com.medexpress.configuration;

import br.com.medexpress.exceptions.ApiException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException exception) {
        final var body = exception.getBody();
        return ResponseEntity.status(exception.getStatus()).body(body);
    }
}
