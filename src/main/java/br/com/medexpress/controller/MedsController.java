package br.com.medexpress.controller;
import br.com.medexpress.domain.request.MedsRequestDTO;
import br.com.medexpress.repository.entities.Meds;
import br.com.medexpress.service.MedsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/meds")
public class MedsController {

    private final MedsService medsService;
    @PostMapping
    public ResponseEntity<Meds> createMeds(@RequestBody @Valid MedsRequestDTO request) {
        return ResponseEntity.status(201).body(medsService.add(request));
        //throw new BadRequestException("Deu errado mané");
    }
}
