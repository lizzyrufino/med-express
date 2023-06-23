package br.com.medexpress.repository.entities;

import br.com.medexpress.enumeration.MedsType;
import br.com.medexpress.enumeration.TagType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

//Essa classe é uma entidade do banco. Em entidades do banco sempre usar o private
//Records não é necessario colocar private, pois é inerente, ela ja é privada.

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@Document(collection = "meds")//entidade do mongo -> nome da tabela que o jpa vai criar/acessar no mongo.
public class Meds {

    @Id //Gera um Id automaticamente
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TagType tag;

    private String dosage;

    private String labs;

    @Enumerated(EnumType.STRING)
    private MedsType type;

    private BigDecimal price;

    private LocalDate dueDate;

    private LocalDate fabricationDate;

    private Integer pillQuantity;
}
