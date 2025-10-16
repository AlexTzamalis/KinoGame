package me.alextzamalis.login;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserManager {
    private List<User> users = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // REGISTER A NEW USER
    public void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter secret question: ");
        String secretQuestion = scanner.nextLine();

        System.out.print("Enter secret anwser: ");
        String secretAnswer = scanner.nextLine();

        users.add(new User(username, password, email, secretQuestion, secretAnswer));
        System.out.println("Registration successful!");
    }
}
