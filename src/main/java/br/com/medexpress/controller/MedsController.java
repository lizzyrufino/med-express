package br.com.medexpress.controller;
import br.com.medexpress.domain.request.MedsRequestDTO;
import br.com.medexpress.exceptions.BadRequestException;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
@RequestMapping("/meds")
public class MedsController {
    @PostMapping
    public ResponseEntity<Void> createMeds(@RequestBody @Valid MedsRequestDTO request) {
        //return ResponseEntity.status(201).build();
        throw new BadRequestException("Deu errado mané");
    }
}
