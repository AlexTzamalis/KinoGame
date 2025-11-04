package me.alextzamalis.database;

import me.alextzamalis.encryption.Encryptor;
import me.alextzamalis.login.User;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Utility class to initialize the database with default admin account.
 * 
 * <p>This class provides a method to create a default administrator account
 * if one doesn't exist in the database. The default admin credentials are:
 * <ul>
 *   <li>Username: admin</li>
 *   <li>Password: admin123</li>
 *   <li>Email: admin@kinogame.com</li>
 * </ul>
 * 
 * <p><strong>Security Note:</strong> In a production environment, the default
 * admin password should be changed immediately after first login.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class DatabaseInitializer {
    
    private static final String DEFAULT_ADMIN_USERNAME = "admin";
    private static final String DEFAULT_ADMIN_PASSWORD = ".!admin!.,.!161104Aa!.";
    private static final String DEFAULT_ADMIN_EMAIL = "admin@kinogame.gr";
    
    /**
     * Initializes the database with a default admin account if one doesn't exist.
     * 
     * @throws IOException if database operations fail
     * @throws NoSuchAlgorithmException if SHA-256 algorithm is not available
     */
    public static void initializeDatabase() throws IOException, NoSuchAlgorithmException {
        UserDatabase database = new UserDatabase();
        Encryptor encryptor = new Encryptor();
        
        // Check if admin account already exists
        User existingAdmin = database.findUserByUsername(DEFAULT_ADMIN_USERNAME);
        if (existingAdmin != null) {
            return; // Admin already exists
        }
        
        // Create default admin account
        String passwordHash = encryptor.encryptString(DEFAULT_ADMIN_PASSWORD);
        UUID adminUUID = UUID.randomUUID();
        
        User adminUser = new User(
            DEFAULT_ADMIN_USERNAME,
            passwordHash,
            DEFAULT_ADMIN_EMAIL,
            adminUUID,
            true,  // isAdmin = true
            0.0,   // initial balance
            LocalDateTime.now()
        );
        
        database.addUser(adminUser);
        System.out.println("Default admin account created:");
        System.out.println("  Username: " + DEFAULT_ADMIN_USERNAME);
        System.out.println("  Password: " + DEFAULT_ADMIN_PASSWORD);
        System.out.println("  Email: " + DEFAULT_ADMIN_EMAIL);
        System.out.println("Please change the password after first login!");
    }
}

