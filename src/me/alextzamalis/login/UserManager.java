package me.alextzamalis.login;

import me.alextzamalis.encryption.Encryptor;
import me.alextzamalis.exceptions.InvalidEmailExcpetion;
import me.alextzamalis.util.Constants;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import java.util.Random;

public class UserManager {

    private List<User> users = new ArrayList<>();                        // all users array
    private Scanner scanner = new Scanner(System.in);                    // user inputs
    private Constants con = new Constants();                             // all in house constants
    private Encryptor encryptor = new Encryptor();
    private Random random = new Random();                                // Generates a random number
    private UUID uuid;                                                   // Generates random UUID with Long numbers

    // REGISTER A NEW USER
    public void registerUser() throws NoSuchAlgorithmException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        repeatPassword(password);
        String passwordToHashed = encryptor.encryptString(password);
        password = null;
        System.out.println("DEBUG" + password + " " + passwordToHashed);

        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        emailInput(email);

        System.out.print("Enter secret question: ");
        String secretQuestion = scanner.nextLine();

        System.out.print("Enter secret anwser: ");
        String secretAnswer = scanner.nextLine();

        uuid = generateUserUUID(this.uuid);

        users.add(new User(username, passwordToHashed, email, secretQuestion, secretAnswer, uuid));
        System.out.println("Registration successful!");
        System.out.println("DEBUG User unique id is: " + uuid );

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
    public void forgetPassword() throws NoSuchAlgorithmException {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username)) {
                System.out.println("Answer the secret question: " + user.getSecretQuestion());
                String answer = scanner.nextLine();

                if (user.getSecretAnswer().equals(answer)) {

                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    repeatPassword(newPassword);
                    String passwordToHashed = encryptor.encryptString(newPassword);
                    newPassword = null;
                    System.out.println("DEBUG: " + newPassword + " " + passwordToHashed);
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

    // START THE USER MANAGEMENT ACTION OPERATION
    public void start() throws NoSuchAlgorithmException {
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
                    forgetPassword();
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

    public void emailInput(String email){
        while(true) {
            try {
                if (isValidEmail(email)) {
                    return;
                } else {
                    throw new InvalidEmailExcpetion("Invalid email format!");
                }

            } catch (InvalidEmailExcpetion e) {
                System.out.println(e.getMessage());
                System.out.print("Try again: ");
                email = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Unexcepted error: " + e.getMessage());
                System.out.print("Try again: ");
                email = scanner.nextLine();
            }
        }
        }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public void repeatPassword(String password) {
        while(password.length() < con.MIN_PASSWORD_DIGITS || password.length() > con.MAX_PASSWORD_DIGITS) {
            System.out.println("Passwords must be atleast 4 digits long up to 48");
            System.out.print("Enter password again: ");
            password = scanner.nextLine();
        }
    }

    public UUID generateUserUUID(UUID uuid) {
        return new UUID(random.nextLong(), random.nextLong());
    }
}

