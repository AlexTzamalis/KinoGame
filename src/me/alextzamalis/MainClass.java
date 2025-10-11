package me.alextzamalis;

import me.alextzamalis.process.User;
import me.alextzamalis.util.MessageUtil;

import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class MainClass {
    public static void main(String[] args) {

        Random random = new Random();
        Scanner scan = new Scanner(System.in);
        UUID uuid = new UUID(random.nextLong(), random.nextLong());

        MessageUtil messageUtil = new MessageUtil();
        User user = new User();


    }

    public static void generateRandomPull(UUID uuid) {
        System.out.println(uuid);

    }

}