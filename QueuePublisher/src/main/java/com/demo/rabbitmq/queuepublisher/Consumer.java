package com.demo.rabbitmq.queuepublisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        System.out.println("before");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            JSONObject json = new JSONObject(message);
            System.out.println("test");
            System.out.println("Message received = " + json);
            System.out.println("Email" + json.getString("email"));
            System.out.println("Query" + json.getString("query"));
            System.out.println("from_date" + json.getString("from_date"));
            System.out.println("to_date" + json.getString("to_date"));
        };

        channel.basicConsume("Queue-1", true, deliverCallback, consumerTag -> {});
        System.out.println("end");
    }
}
