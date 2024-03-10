package org.example;

import com.google.gson.Gson;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.util.List;
/**
 * Project: Lab4
 * Purpose Details: Sends pizza orders to a RabbitMQ queue.
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 2024-03-10
 * Last Date Changed: 2024-03-10
 * Rev: 1.0
 */

public class Send {
    // Name of the RabbitMQ queue for pizza orders
    private final static String QUEUE_NAME = "pizza_queue";

    public static void main(String[] argv) throws Exception {
        // Create connection factory and set RabbitMQ host
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // Create a Pizza object (replace this with your actual Pizza object creation logic)
            Pizza pizzaToSend = createPizza();

            // Serialize the Pizza object into a JSON message
            String jsonMessage = serializePizza(pizzaToSend);

            // Publish the JSON message to the RabbitMQ queue
            channel.basicPublish("", QUEUE_NAME, null, jsonMessage.getBytes());
            System.out.println(" [x] Sent Pizza order: " + pizzaToSend.getPizzaName());
        }
    }

    // Method to create a Pizza object
    private static Pizza createPizza() {
        Pizza pizza = new Pizza();
        pizza.setPizzaName("Pepperoni");
        pizza.setToppings(List.of("Cheese", "Pepperoni"));
        return pizza;
    }

    // Method to serialize the Pizza object into a JSON message
    private static String serializePizza(Pizza pizza) {
        Gson gson = new Gson();
        return Pizza.serializeToJson(pizza);
    }
}