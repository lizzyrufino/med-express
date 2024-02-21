package br.com.medexpress.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfiguration {

    private final ApplicationConfig applicationConfig;

    @Bean
    public RabbitAdmin amqpAdmin(ConnectionFactory connectionFactory){ //O connection factory serve para pegar as variaveis de ambiente do application.yaml
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter){ //converte toda mensagem trafegada entre o java e o rabbit em json, pois ele trafega bite
        final var rabbitTemplateBuild = new RabbitTemplate(connectionFactory);
        rabbitTemplateBuild.setMessageConverter(converter); //conversao
        return rabbitTemplateBuild;
    }
    @Bean //instanciar o conversor
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }
    // ---------- Inicio da configuração de exchange ------------

    @Bean
    public DirectExchange medsRequestedQueueExchange(){
        return new DirectExchange(applicationConfig.getMedsRequestedQueueExchange());
    }
    @Bean //metodo de criaçao da fila
    public Queue medsRequestedQueue(){
        return QueueBuilder
                .durable(applicationConfig.getMedsRequestedQueue())
                .deadLetterExchange(applicationConfig.getMedsRequestedQueueExchange())
                .deadLetterRoutingKey(applicationConfig.getMedsRequestedQueueDlq())
                .build();
    }
    @Bean
    public Queue medsRequestedQueueDlq(){
        return QueueBuilder
                .durable(applicationConfig.getMedsRequestedQueueDlq())
                .build();
    }

    @Bean //criacao da binding para vincular a fila ao exchange;
    public Binding medsRequestedQueueBinding(){
        return BindingBuilder.bind(medsRequestedQueue()).to(medsRequestedQueueExchange()).withQueueName();
    }

    @Bean //criacao da binding para vincular a dlq ao exchange;
    public Binding medsRequestedQueueDlqBinding(){
        return BindingBuilder.bind(medsRequestedQueueDlq()).to(medsRequestedQueueExchange()).withQueueName();
    }

}
