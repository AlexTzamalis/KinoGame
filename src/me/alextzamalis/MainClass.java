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
        System.out.println(uuid);

        User user = new User();

        Date curretDate = new Date();
        System.out.println(curretDate);

        //variables
        String userName;
        int userAge;
        String userPassword; // convert to hash!!!

        System.out.println("WELCOME TO RANDOM GAME!");
        System.out.println("Before starting we need your personal information and your consent!");
        System.out.println();
        System.out.print("Name: ");
        userName = scan.nextLine();

        System.out.print("Age: ");
        userAge = scan.nextByte();
        while (userAge < 21) {
            System.out.print("Name above 21: ");
            userAge = scan.nextByte();
        }

        scan.nextLine(); // clears nextline
        System.out.print("Password: ");
        userPassword = scan.nextLine();
        while(userPassword.length() < 8) {
            System.out.print("Password must be 8 digits lond and above: ");
            userPassword = scan.nextLine();
        }

        generateRandomPull(uuid);


    }

    public static void generateRandomPull(UUID uuid) {
        System.out.println(uuid);

    }
}