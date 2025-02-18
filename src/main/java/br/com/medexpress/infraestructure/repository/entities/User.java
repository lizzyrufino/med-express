package br.com.medexpress.infraestructure.repository.entities;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Builder
@AllArgsConstructor
@Document(collection="users")
public class User {
    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String email;
    private String name;
    private String password;
    private String saltKey;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

}
