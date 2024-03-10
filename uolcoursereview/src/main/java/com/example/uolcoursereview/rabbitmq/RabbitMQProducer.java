package com.example.uolcoursereview.rabbitmq;

import com.example.uolcoursereview.dto.CourseReviewES;
import com.example.uolcoursereview.dto.CourseReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.es.name}")
    private String exchange;

    @Value("${rabbitmq.routing.es.key}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    private RabbitTemplate rabbitTemplate;

    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(CourseReviewES courseReviewES) {
        LOGGER.info(String.format("Message sent -> %s", courseReviewES.toString()));
        LOGGER.info("courseReviewES id: {}, name: {}, review: {}", courseReviewES.getId(), courseReviewES.getCourseCode(), courseReviewES.getReview());
        rabbitTemplate.convertAndSend(exchange, routingKey, courseReviewES);
    }
}
