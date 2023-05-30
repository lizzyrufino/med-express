package br.com.medexpress.domain.request;

import br.com.medexpress.enumeration.MedsType;
import br.com.medexpress.enumeration.TagType;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;


//DTO
@Builder
public record MedsRequestDTO(
        @NotNull
        @NotEmpty
        String name,
        @NotNull
        TagType tag,
        @NotNull
        @NotEmpty
        String dosage,
        @NotNull
        @NotEmpty
        String labs,
        @NotNull
        MedsType type,
        @NotNull
        @Min(0)
        BigDecimal price,
        @NotNull
        @Future
        LocalDate dueDate,
        @Past
        LocalDate fabricationDate,
        Integer pillQuantity

        ){

}
