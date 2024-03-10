package com.example.uolcoursereview.rabbitmq;

import com.example.uolcoursereview.dto.CourseReviewES;
import com.example.uolcoursereview.elasticsearch.ElasticSearchQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RabbitMQConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @Autowired
    private ElasticSearchQuery esQuery;

    @RabbitListener(queues = {"${rabbitmq.queue.es.name}"})
    public void consume(CourseReviewES courseReviewES) throws IOException {
        LOGGER.info(String.format("Received message -> %s", courseReviewES.toString()));
        LOGGER.info("courseReviewES id: {}, name: {}, review: {}", courseReviewES.getId(), courseReviewES.getCourseCode(), courseReviewES.getReview());

        // create
        esQuery.createOrUpdateDocument(courseReviewES);
    }
}
