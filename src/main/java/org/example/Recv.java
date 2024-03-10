package org.example;

import com.rabbitmq.client.*;
import com.rabbitmq.client.Channel;

/**
 * Project: Lab4
 * Purpose Details: Receives and processes pizza orders from a RabbitMQ queue.
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 2024-03-10
 * Last Date Changed: 2024-03-10
 * Rev: 1.0
 */

public class Recv {

    private final static String QUEUE_NAME = "pizza_queue";

    public static void main(String[] argv) throws Exception {
        // Set up RabbitMQ connection factory
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            // Declare the queue
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            System.out.println(" [*] Waiting for pizza orders. To exit press CTRL+C");

            // Set up a callback for incoming messages
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                byte[] messageBytes = delivery.getBody();
                String message = new String(messageBytes, "UTF-8");

                // Deserialize the received JSON message into a Pizza object
                Pizza receivedPizza = deserializePizza(message);

                // Print the received Pizza object details
                System.out.println(" [x] Received Pizza Order:");
                receivedPizza.displayPizzaDetails();
            };
            // Start consuming messages from queue
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        }
    }

    // Add a method to deserialize the JSON message into a Pizza object
    private static Pizza deserializePizza(String jsonMessage) {
        return Pizza.deserializeFromJson(jsonMessage);
    }
}
