package br.com.medexpress.repository;

import br.com.medexpress.repository.entities.Meds;
import org.springframework.data.mongodb.repository.MongoRepository;

//O JPA do mongo só usa interface, então sempre será interface e não classe.

//A entidade que vai gerenciar , o tipo do ID (Meds, String)
public interface MedsRepository extends MongoRepository<Meds, String>{

}
