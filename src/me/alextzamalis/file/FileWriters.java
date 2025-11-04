package me.alextzamalis.file;

import me.alextzamalis.util.Constants;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for writing user data to files.
 * 
 * <p>This class handles file operations for persisting user credentials and data.
 * It uses BufferedWriter for efficient file writing operations.
 * 
 * <p><strong>Note:</strong> This class is currently not integrated into the main
 * user management flow. Files are not automatically persisted when users register.
 * This could be added as a future enhancement.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class FileWriters {

    /**
     * Writes user credentials to a file.
     * 
     * <p>This method writes the provided credentials array to the credentials file.
     * Each element of the array is written on a separate line.
     * 
     * @param collectedUserCredentials array containing user credentials to write
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeUserCredentialsAsString(String[] collectedUserCredentials) throws IOException {
        if (collectedUserCredentials == null) {
            throw new IllegalArgumentException("Credentials array cannot be null");
        }

        // Use try-with-resources to ensure proper resource cleanup
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.USER_CREDENTIALS_FILE, true))) { // true = append mode
            
            // Write each credential on a separate line
            for (String credential : collectedUserCredentials) {
                if (credential != null) {
                    writer.write(credential);
                    writer.newLine();
                }
            }
            writer.flush(); // Ensure data is written immediately
        }
    }

    /**
     * Writes user data to a file.
     * 
     * <p>This method is a placeholder for future implementation.
     * It can be used to write additional user information beyond credentials.
     * 
     * @param userData the user data to write
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void writeUserData(String userData) throws IOException {
        if (userData == null || userData.trim().isEmpty()) {
            throw new IllegalArgumentException("User data cannot be null or empty");
        }

        // Use try-with-resources to ensure proper resource cleanup
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.USER_DATA_FILE, true))) { // true = append mode
            
            writer.write(userData);
            writer.newLine();
            writer.flush(); // Ensure data is written immediately
        }
    }
}
