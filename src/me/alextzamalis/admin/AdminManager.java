package me.alextzamalis.admin;

import me.alextzamalis.database.UserDatabase;
import me.alextzamalis.login.User;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * Manages administrative operations for user accounts.
 * 
 * <p>This class provides functionality for administrators to:
 * <ul>
 *   <li>View all users in the system</li>
 *   <li>Modify user account balances</li>
 *   <li>Delete user accounts</li>
 *   <li>Grant/revoke admin privileges</li>
 * </ul>
 * 
 * <p><strong>Security:</strong> All operations require admin privileges
 * and should only be accessible to administrators.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class AdminManager {
    
    private UserDatabase database;
    private Scanner scanner;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Constructs a new AdminManager instance.
     * 
     * @param database the UserDatabase instance to use for operations
     * @param scanner the Scanner instance for user input
     */
    public AdminManager(UserDatabase database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }
    
    /**
     * Displays the admin management menu and handles user input.
     * 
     * @throws IOException if database operations fail
     */
    public void showMenu() throws IOException {
        while (true) {
            System.out.println("\n=== User Management ===");
            System.out.println("1. View All Users");
            System.out.println("2. Modify User Balance");
            System.out.println("3. Delete User Account");
            System.out.println("4. View User Details");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                
                switch (choice) {
                    case 1:
                        viewAllUsers();
                        break;
                    case 2:
                        modifyUserBalance();
                        break;
                    case 3:
                        deleteUserAccount();
                        break;
                    case 4:
                        viewUserDetails();
                        break;
                    case 5:
                        return; // Back to main menu
                    default:
                        System.out.println("Invalid choice. Please select 1-5.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }
    
    /**
     * Displays a list of all users in the system.
     * 
     * @throws IOException if database operations fail
     */
    public void viewAllUsers() throws IOException {
        List<User> users = database.loadUsers();
        
        if (users.isEmpty()) {
            System.out.println("\nNo users found in the system.");
            return;
        }
        
        System.out.println("\n=== All Users ===");
        System.out.printf("%-20s %-30s %-10s %-15s %-12s%n", 
                "Username", "Email", "Admin", "Balance", "Registered");
        System.out.println("-".repeat(90));
        
        for (User user : users) {
            System.out.printf("%-20s %-30s %-10s $%-14.2f %-12s%n",
                    user.getUsername(),
                    user.getEmail(),
                    user.isAdmin() ? "Yes" : "No",
                    user.getAccountBalance(),
                    user.getRegistrationTime().format(DATE_FORMATTER));
        }
    }
    
    /**
     * Allows admin to modify a user's account balance.
     * 
     * @throws IOException if database operations fail
     */
    public void modifyUserBalance() throws IOException {
        System.out.print("\nEnter username to modify balance: ");
        String username = scanner.nextLine().trim();
        
        User user = database.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        System.out.println("Current balance: $" + user.getAccountBalance());
        System.out.print("Enter new balance: $");
        
        try {
            double newBalance = Double.parseDouble(scanner.nextLine().trim());
            
            if (newBalance < 0) {
                System.out.println("Balance cannot be negative.");
                return;
            }
            
            user.setAccountBalance(newBalance);
            database.updateUser(user);
            
            System.out.println("Balance updated successfully. New balance: $" + newBalance);
        } catch (NumberFormatException e) {
            System.out.println("Invalid balance amount.");
        }
    }
    
    /**
     * Allows admin to delete a user account.
     * 
     * @throws IOException if database operations fail
     */
    public void deleteUserAccount() throws IOException {
        System.out.print("\nEnter username to delete: ");
        String username = scanner.nextLine().trim();
        
        User user = database.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        System.out.println("Are you sure you want to delete user: " + username + "?");
        System.out.print("Type 'yes' to confirm: ");
        String confirmation = scanner.nextLine().trim();
        
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("Deletion cancelled.");
            return;
        }
        
        List<User> users = database.loadUsers();
        users.removeIf(u -> u.getUUID().equals(user.getUUID()));
        database.saveUsers(users);
        
        System.out.println("User account deleted successfully.");
    }
    
    /**
     * Displays detailed information about a specific user.
     * 
     * @throws IOException if database operations fail
     */
    public void viewUserDetails() throws IOException {
        System.out.print("\nEnter username to view details: ");
        String username = scanner.nextLine().trim();
        
        User user = database.findUserByUsername(username);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }
        
        System.out.println("\n=== User Details ===");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("UUID: " + user.getUUID());
        System.out.printf("Account Balance: $%.2f%n", user.getAccountBalance());
        System.out.println("Registration Date: " + user.getRegistrationTime().format(DATE_FORMATTER));
        System.out.println("Account Type: " + (user.isAdmin() ? "Administrator" : "Regular User"));
    }
}

