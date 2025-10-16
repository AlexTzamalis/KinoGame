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

    // USER LOGIN
    public void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for(User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Login Successful!");
                return; // finished this method task so only login will be printed!
            }
        }
        System.out.println("Login failed. Username or password is incorrect.");
    }

    // FORGET PASSWORD ACTION OPERATION
    public void forgetPassword() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Answer the secret question: " + user.getSecretQuestion());
                String answer = scanner.nextLine();

                if (user.getSecretAnswer().equals(answer)) {

                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    user.setPassword(newPassword);

                    System.out.println("Password reset successful.");
                    return; // exit from the method
                }
                else  {
                    System.out.println("Incorrect answer. ");
                    return; // incorrect and exits
                }
            }
        }
        System.out.println("User not found");
    }

    public void start() {
        while(true) {
            System.out.println("\nUser Management System");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Forget Password");
            System.out.println("4. Exit");
            System.out.print("Entert choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline left over..

            switch (choice) {
                case 1:
                    registerUser();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return; // gets out of the start method so it stops the application.
                default:
                    System.out.println("Invalid choice, Please select 1 , 2 , 3 or 4 ");
                    break;
            }
        }
    }
}

