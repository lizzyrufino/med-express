package br.com.medexpress.infraestructure.amqp.rabbitmq.adapter;


//publisher vai utilizar esse adapter para enviar as mensagens para a fila.
//para o caso de ter mais de um publisher. Caso contrario da pra fazer direto na publisher.

import br.com.medexpress.ports.RabbitMqAdapterInterface;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMqAdapter implements RabbitMqAdapterInterface {

    //faz a publicação dos eventos
    private final RabbitOperations rabbitOperations;
    @Override
    public void sendMessage(String exchangeName, String routingKey, Object message) {

    }
}
