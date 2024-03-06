package com.example.uolcoursereview.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String searchQueue;

    @Value("${rabbitmq.exchange.name}")
    private String searchExchange;

    @Value("${rabbitmq.routing.key}")
    private String searchRoutingKey;


    @Value("${rabbitmq.queue.es.name}")
    private String esQueue;

    @Value("${rabbitmq.exchange.es.name}")
    private String esExchange;

    @Value("${rabbitmq.routing.es.key}")
    private String esRoutingKey;

    @Bean
    public Queue queue() {
        return new Queue(searchQueue);
    };

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(searchExchange);
    }

    @Bean
    public Queue esQueue() {
        return new Queue(esQueue);
    };

    @Bean
    public TopicExchange esExchange() {
        return new TopicExchange(esExchange);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(searchRoutingKey);
    }

    @Bean
    public Binding esBinding() {
        return BindingBuilder
                .bind(esQueue())
                .to(esExchange())
                .with(esRoutingKey);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
