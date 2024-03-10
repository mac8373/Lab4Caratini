package org.example;

/**
 * Project: Lab4
 * Purpose Details: For coordinating sending and receiving of pizza orders
 * Course: IST 242
 * Author: Maximo Caratini
 * Date Developed: 2024-03-10
 * Last Date Changed: 2024-03-10
 * Rev: 1.0
 */

/**
 * Main method, being the entry point of this program.
 */
public class Main {
    public static void main(String[] args) {
        // Create a new thread to receiving pizza orders
        Thread recieveThread = new Thread(() -> {
            try {
                Recv.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // New thread for sending pizza orders
        Thread sendThread = new Thread(() -> {
            try {
                Send.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Start threads
        recieveThread.start();
        sendThread.start();
    }
}