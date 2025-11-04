package me.alextzamalis.application;

import me.alextzamalis.admin.AdminManager;
import me.alextzamalis.database.UserDatabase;
import me.alextzamalis.login.User;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main application class that runs after user authentication.
 * 
 * <p>This class provides the main application loop with different menus
 * for regular users and administrators. It handles user interactions
 * and delegates admin functions to AdminManager.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class Application {
    
    private User currentUser;
    private Scanner scanner;
    private UserDatabase database;
    private AdminManager adminManager;
    
    /**
     * Constructs a new Application instance.
     * 
     * @param user the authenticated user to run the application for
     */
    public Application(User user) {
        this.currentUser = user;
        this.scanner = new Scanner(System.in);
        this.database = new UserDatabase();
        this.adminManager = new AdminManager(database, scanner);
    }
    
    /**
     * Starts the main application loop.
     * 
     * <p>This method displays different menus based on whether the user
     * is an administrator or regular user, and handles user input accordingly.
     * 
     * @throws IOException if database operations fail
     */
    public void start() throws IOException {
        System.out.println("\n=== Welcome to Kino Game Application ===");
        System.out.println("Logged in as: " + currentUser.getUsername());
        if (currentUser.isAdmin()) {
            System.out.println("Role: Administrator");
        }
        
        while (true) {
            if (currentUser.isAdmin()) {
                displayAdminMenu();
            } else {
                displayUserMenu();
            }
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                boolean shouldContinue = false;
                
                if (currentUser.isAdmin()) {
                    shouldContinue = handleAdminChoice(choice);
                } else {
                    shouldContinue = handleUserChoice(choice);
                }
                
                if (!shouldContinue) {
                    break; // Exit application
                }
                
                // Refresh user data from database to get latest balance
                currentUser = database.findUserByUsername(currentUser.getUsername());
                
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
        
        System.out.println("Thank you for using Kino Game Application!");
    }
    
    /**
     * Displays the menu for regular users.
     */
    private void displayUserMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. View Account Balance");
        System.out.println("2. View Account Info");
        System.out.println("3. Logout");
        System.out.print("Enter choice: ");
    }
    
    /**
     * Displays the menu for administrators.
     */
    private void displayAdminMenu() {
        System.out.println("\n=== Admin Menu ===");
        System.out.println("1. View Account Balance");
        System.out.println("2. View Account Info");
        System.out.println("3. Manage Users");
        System.out.println("4. View All Users");
        System.out.println("5. Logout");
        System.out.print("Enter choice: ");
    }
    
    /**
     * Handles menu choices for regular users.
     * 
     * @param choice the user's menu choice
     * @return true if application should continue, false if user wants to logout
     * @throws IOException if database operations fail
     */
    private boolean handleUserChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                viewAccountBalance();
                return true;
            case 2:
                viewAccountInfo();
                return true;
            case 3:
                return false; // Logout
            default:
                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                return true;
        }
    }
    
    /**
     * Handles menu choices for administrators.
     * 
     * @param choice the admin's menu choice
     * @return true if application should continue, false if admin wants to logout
     * @throws IOException if database operations fail
     */
    private boolean handleAdminChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                viewAccountBalance();
                return true;
            case 2:
                viewAccountInfo();
                return true;
            case 3:
                adminManager.showMenu();
                return true;
            case 4:
                adminManager.viewAllUsers();
                return true;
            case 5:
                return false; // Logout
            default:
                System.out.println("Invalid choice. Please select 1, 2, 3, 4, or 5.");
                return true;
        }
    }
    
    /**
     * Displays the current user's account balance.
     */
    private void viewAccountBalance() {
        System.out.println("\n=== Account Balance ===");
        System.out.printf("Current Balance: $%.2f%n", currentUser.getAccountBalance());
    }
    
    /**
     * Displays detailed account information for the current user.
     */
    private void viewAccountInfo() {
        System.out.println("\n=== Account Information ===");
        System.out.println("Username: " + currentUser.getUsername());
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("UUID: " + currentUser.getUUID());
        System.out.printf("Account Balance: $%.2f%n", currentUser.getAccountBalance());
        System.out.println("Registration Date: " + currentUser.getRegistrationTime());
        System.out.println("Account Type: " + (currentUser.isAdmin() ? "Administrator" : "Regular User"));
    }
    
    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}

