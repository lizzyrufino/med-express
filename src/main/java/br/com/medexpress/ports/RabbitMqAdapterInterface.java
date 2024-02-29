package br.com.medexpress.ports;

public interface RabbitMqAdapterInterface {
    void sendMessage(String exchangeName, String routingKey, Object message);

}
