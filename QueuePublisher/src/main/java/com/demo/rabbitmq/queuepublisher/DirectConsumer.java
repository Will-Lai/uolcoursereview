package com.demo.rabbitmq.queuepublisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class DirectConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        System.out.println("before");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("Message received = " + message);
        };
        channel.basicConsume("TV", true, deliverCallback, consumerTag -> {});
        channel.close();
        connection.close();
    }
}
