package me.alextzamalis;

import me.alextzamalis.process.UserProcess;
import me.alextzamalis.util.MessageUtil;

import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class MainClass {
    public static void main(String[] args) {

        Random random = new Random();                                   // Generates a random number
        Scanner scan = new Scanner(System.in);                          // User input (might remove it from the main class
        UUID uuid = new UUID(random.nextLong(), random.nextLong());     // Generates random UUID with Long numbers
        MessageUtil messageUtil = new MessageUtil();                    // Calls few starting messages in app startup
        UserProcess user = new UserProcess();                           // starts off for user sign up/sign in
    }

}