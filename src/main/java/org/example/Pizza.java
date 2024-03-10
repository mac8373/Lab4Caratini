package org.example;

import java.io.Serializable;
import java.util.List;
import com.google.gson.Gson;

/**
 * Project: Lab4
 * Purpose Details: Represents a pizza object.
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 2024-03-10
 * Last Date Changed: 2024-03-10
 * Rev: 1.0
 */

public class Pizza implements Serializable {

    private String pizzaName;
    private List<String> toppings;

    // Constructors
    public Pizza() {
        // Default constructor
    }

    public Pizza(String pizzaName, List<String> toppings) {
        this.pizzaName = pizzaName;
        this.toppings = toppings;
    }

    // Getter and Setter methods
    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public List<String> getToppings() {
        return toppings;
    }

    public void setToppings(List<String> toppings) {
        this.toppings = toppings;
    }

    /**
     * Display pizza details.
     */
    public void displayPizzaDetails() {
        System.out.println("Pizza: " + pizzaName);
        System.out.println("Toppings: " + toppings);
    }

    /**
     * Serialize the Pizza object into JSON string.
     * @param pizza The Pizza object to be serialized.
     * @return The JSON string representing serialized Pizza.
     */
    public static String serializeToJson(Pizza pizza) {
        Gson gson = new Gson();
        return gson.toJson(pizza);
    }
    /**
     * Deserialize a JSON string into a Pizza object.
     *
     * @param json The JSON string representing a Pizza object.
     * @return The deserialized Pizza object.
     */
    public static Pizza deserializeFromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Pizza.class);
    }
}
