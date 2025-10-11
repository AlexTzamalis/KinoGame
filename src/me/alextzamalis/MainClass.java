package me.alextzamalis;

import me.alextzamalis.process.User;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class MainClass {
    public static void main(String[] args) {

        // object
        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        UUID uuid = new UUID(random.nextLong(), random.nextLong());

        User user = new User();

        Date curretDate = new Date();
        System.out.println(curretDate);

        //generateRandomPull(uuid);

    }

    public static void generateRandomPull(UUID uuid) {
        System.out.println(uuid);

    }
}