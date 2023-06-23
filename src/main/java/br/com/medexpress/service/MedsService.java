package br.com.medexpress.service;

import br.com.medexpress.domain.request.MedsRequestDTO;
import br.com.medexpress.repository.MedsRepository;
import br.com.medexpress.repository.entities.Meds;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

//Na service fazemos a regra de negócio.
@Service
@RequiredArgsConstructor
@Slf4j
public class MedsService {
    private final MedsRepository medsRepository;
    public Meds add(MedsRequestDTO dto) {
        final var entity = Meds.builder().name(dto.name())
                .labs(dto.labs())
                .tag(dto.tag())
                .price(dto.price())
                .type(dto.type())
                .dueDate(dto.dueDate())
                .dosage(dto.dosage())
                .fabricationDate(dto.fabricationDate())
                .pillQuantity(dto.pillQuantity())
                .build();
        return medsRepository.save(entity);
    }
}
