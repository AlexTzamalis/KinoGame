package me.alextzamalis;

import me.alextzamalis.application.Application;
import me.alextzamalis.database.DatabaseInitializer;
import me.alextzamalis.login.User;
import me.alextzamalis.login.UserManager;
import me.alextzamalis.util.MessageUtil;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Main entry point for the Kino Game application.
 * 
 * <p>This application provides a command-line interface for:
 * <ul>
 *   <li>User registration with validation</li>
 *   <li>User authentication (login)</li>
 *   <li>Main application functionality after authentication</li>
 *   <li>Administrative functions for admin users</li>
 * </ul>
 * 
 * <p>The application flow:
 * <ol>
 *   <li>User registers or logs in</li>
 *   <li>Main application starts with user-specific menu</li>
 *   <li>User can perform actions based on their role (regular user or admin)</li>
 * </ol>
 * 
 * <p>The application uses SHA-256 hashing for password security and validates
 * all user inputs according to configured constraints. User data is persisted
 * to a local database file.
 * 
 * @author AlexTzamalis
 * @version 2.0
 */
public class MainClass {
    
    /**
     * Main method that initializes and starts the application.
     * 
     * <p>This method:
     * <ol>
     *   <li>Displays welcome messages</li>
     *   <li>Starts the user management system (login/registration)</li>
     *   <li>Launches the main application after successful authentication</li>
     * </ol>
     * 
     * @param args command line arguments (not currently used)
     */
    public static void main(String[] args) {
        try {
            // Initialize database with default admin account if needed
            DatabaseInitializer.initializeDatabase();
            
            // Initialize message utility for console output
            MessageUtil messageUtil = new MessageUtil();
            
            // Display welcome messages
            messageUtil.currentDate();
            messageUtil.welcomeMesasge();

            // Initialize and start the user management system
            UserManager manager = new UserManager();
            User authenticatedUser = manager.start();
            
            // If user successfully logged in or registered, start the main application
            if (authenticatedUser != null) {
                Application app = new Application(authenticatedUser);
                app.start();
                app.closeScanner();
            }
            
            // Cleanup
            manager.closeScanner();
            
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: SHA-256 algorithm not available. " + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error: Database operation failed. " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
