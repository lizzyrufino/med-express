package br.com.medexpress.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//Classe de configuração (ex: para uso de variaveis de ambiente)
@Getter
@Configuration
public class ApplicationConfig {

    @Value("${message-broker.meds-requested-queue}")
    private String medsRequestedQueue;

    @Value("${message-broker.meds-requested-queue-dlq}")
    private String medsRequestedQueueDlq;

    @Value("${message-broker.meds-requested-queue-exchange}")
    private String medsRequestedQueueExchange;

    @Value("${security.secret-key}")
    private String secretKey;

    @Value("${security.expires-in}")
    private Long expiresIn;
    @Value("${security.crypt.algorithm}")
    private String algorithm;

    @Value("${security.crypt.cipher}")
    private String cipher;

    @Value("${security.crypt.public-key}")
    private String publicKey;
    @Value("${security.crypt.private-key}")
    private String privateKey;

}
