package me.alextzamalis.login;

import me.alextzamalis.database.UserDatabase;
import me.alextzamalis.encryption.Encryptor;
import me.alextzamalis.exceptions.InvalidEmailException;
import me.alextzamalis.util.Constants;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

/**
 * Manages user registration and authentication operations.
 * 
 * <p>This class handles the core user management functionality including:
 * <ul>
 *   <li>User registration with validation</li>
 *   <li>User authentication (login)</li>
 *   <li>Duplicate username/email checking</li>
 *   <li>Integration with persistent database storage</li>
 * </ul>
 * 
 * <p><strong>Security Considerations:</strong>
 * <ul>
 *   <li>Passwords are stored as SHA-256 hashes, never in plain text</li>
 *   <li>Password validation ensures minimum and maximum length requirements</li>
 *   <li>Email validation prevents invalid email formats</li>
 *   <li>All data is persisted to a local database file</li>
 * </ul>
 * 
 * @author AlexTzamalis
 * @version 2.0
 */
public class UserManager {

    private Scanner scanner = new Scanner(System.in);
    private Encryptor encryptor = new Encryptor();
    private UserDatabase database = new UserDatabase();

    /**
     * Registers a new user with username, password, and email.
     * 
     * <p>This method:
     * <ol>
     *   <li>Validates username uniqueness</li>
     *   <li>Validates password strength and confirms it</li>
     *   <li>Validates email format and uniqueness</li>
     *   <li>Hashes the password before storage</li>
     *   <li>Generates a unique UUID for the user</li>
     *   <li>Saves user to persistent database</li>
     * </ol>
     * 
     * @return the newly created User object, or null if registration failed
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     * @throws IOException if database operation fails
     */
    public User registerUser() throws NoSuchAlgorithmException, IOException {
        // Get and validate username
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        
        // Check for duplicate username
        if (database.isUsernameTaken(username)) {
            System.out.println("Username already taken. Please choose another.");
            return null;
        }
        
        // Validate username length
        if (username.length() < Constants.MIN_NAME_CHAR || username.length() > Constants.MAX_NAME_CHAR) {
            System.out.println("Username must be between " + Constants.MIN_NAME_CHAR + 
                             " and " + Constants.MAX_NAME_CHAR + " characters.");
            return null;
        }

        // Get and validate password
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        password = validateAndConfirmPassword(password);
        
        if (password == null) {
            System.out.println("Registration cancelled due to password validation failure.");
            return null;
        }
        
        // Hash password before storing
        String passwordHash = encryptor.encryptString(password);
        // Clear password from memory as soon as possible
        password = null;

        // Get and validate email
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        email = validateEmail(email);
        
        if (email == null) {
            System.out.println("Registration cancelled due to email validation failure.");
            return null;
        }
        
        // Check for duplicate email
        if (database.isEmailTaken(email)) {
            System.out.println("Email already registered. Please use a different email.");
            return null;
        }

        // Generate unique UUID for user
        UUID uuid = UUID.randomUUID();
        
        // Create user with default values: not admin, balance 0.0, current time
        User newUser = new User(username, passwordHash, email, uuid, false, 0.0, LocalDateTime.now());
        
        // Save to database
        database.addUser(newUser);
        
        System.out.println("Registration successful!");
        return newUser;
    }

    /**
     * Authenticates a user by username and password.
     * 
     * <p>This method compares the provided password (after hashing) with the stored
     * password hash. Passwords are never compared in plain text.
     * 
     * @return the authenticated User object if login succeeds, null otherwise
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     * @throws IOException if database operation fails
     */
    public User login() throws NoSuchAlgorithmException, IOException {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Hash the provided password to compare with stored hash
        String passwordHash = encryptor.encryptString(password);
        password = null; // Clear password from memory

        // Find user in database
        User user = database.findUserByUsername(username);
        
        if (user != null && user.getPassword().equals(passwordHash)) {
            System.out.println("Login Successful! Welcome, " + username + "!");
            return user;
        }
        
        System.out.println("Login failed. Username or password is incorrect.");
        return null;
    }

    /**
     * Starts the user management system main menu loop.
     * 
     * <p>This method displays a menu and handles user input for:
     * <ul>
     *   <li>User registration</li>
     *   <li>User login</li>
     *   <li>Application exit</li>
     * </ul>
     * 
     * @return the authenticated User object after successful login/registration, null if user exits
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     * @throws IOException if database operation fails
     */
    public User start() throws NoSuchAlgorithmException, IOException {
        while (true) {
            System.out.println("\n=== User Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());

                switch (choice) {
                    case 1:
                        User newUser = registerUser();
                        if (newUser != null) {
                            // Load fresh user data from database to ensure consistency
                            return database.findUserByUsername(newUser.getUsername());
                        }
                        break;
                    case 2:
                        User loggedInUser = login();
                        if (loggedInUser != null) {
                            // Load fresh user data from database to ensure consistency
                            return database.findUserByUsername(loggedInUser.getUsername());
                        }
                        break;
                    case 3:
                        System.out.println("Exiting...");
                        closeScanner();
                        return null;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1-3).");
            }
        }
    }

    /**
     * Validates email format and prompts for re-entry if invalid.
     * 
     * @param email the email to validate
     * @return the validated email, or null if validation fails repeatedly
     */
    private String validateEmail(String email) {
        while (true) {
            try {
                if (isValidEmail(email)) {
                    return email;
                } else {
                    throw new InvalidEmailException("Invalid email format!");
                }
            } catch (InvalidEmailException e) {
                System.out.println(e.getMessage());
                System.out.print("Try again (or 'cancel' to abort): ");
                email = scanner.nextLine().trim();
                
                if (email.equalsIgnoreCase("cancel")) {
                    return null;
                }
            }
        }
    }

    /**
     * Validates email format using regex pattern.
     * 
     * @param email the email to validate
     * @return true if email format is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }

    /**
     * Validates password length and confirms it by asking user to re-enter.
     * 
     * @param password the initial password entry
     * @return the validated and confirmed password, or null if validation fails
     */
    private String validateAndConfirmPassword(String password) {
        // Validate password length
        while (password.length() < Constants.MIN_PASSWORD_DIGITS || 
               password.length() > Constants.MAX_PASSWORD_DIGITS) {
            System.out.println("Password must be between " + Constants.MIN_PASSWORD_DIGITS + 
                             " and " + Constants.MAX_PASSWORD_DIGITS + " characters.");
            System.out.print("Enter password again (or 'cancel' to abort): ");
            password = scanner.nextLine();
            
            if (password.equalsIgnoreCase("cancel")) {
                return null;
            }
        }

        // Confirm password
        System.out.print("Confirm password: ");
        String confirmPassword = scanner.nextLine();

        if (!password.equals(confirmPassword)) {
            System.out.println("Passwords do not match!");
            return null;
        }

        return password;
    }

    /**
     * Closes the scanner resource.
     * Should be called when the application is terminating.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
