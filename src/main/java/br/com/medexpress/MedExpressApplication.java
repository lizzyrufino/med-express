package br.com.medexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@EnableMongoAuditing
@EnableJpaRepositories
@EnableMongoRepositories(basePackages = "br.com.medexpress.repository")
@SpringBootApplication(scanBasePackages = {"br.com.medexpres"}) //inicia o spring, precisa estar em cima da primeira classe
public class MedExpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedExpressApplication.class, args);
	}

}
