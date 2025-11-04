package me.alextzamalis.database;

import me.alextzamalis.login.User;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * Database layer for persistent user storage.
 * 
 * <p>This class handles saving and loading users from a local file-based database.
 * Users are stored in a structured format that persists across application restarts.
 * 
 * <p><strong>File Format:</strong>
 * Each user is stored on a single line with fields separated by "|||":
 * username|||passwordHash|||email|||uuid|||isAdmin|||accountBalance|||registrationTime
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class UserDatabase {
    
    private static final String DATABASE_FILE = "users.db";
    private static final String FIELD_SEPARATOR = "|||";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    
    /**
     * Loads all users from the database file.
     * 
     * @return a list of all users in the database
     * @throws IOException if an error occurs while reading the file
     */
    public List<User> loadUsers() throws IOException {
        List<User> users = new ArrayList<>();
        Path dbPath = Paths.get(DATABASE_FILE);
        
        // If database file doesn't exist, return empty list
        if (!Files.exists(dbPath)) {
            return users;
        }
        
        try (BufferedReader reader = Files.newBufferedReader(dbPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                try {
                    User user = parseUser(line);
                    if (user != null) {
                        users.add(user);
                    }
                } catch (Exception e) {
                    System.err.println("Error parsing user line: " + e.getMessage());
                    // Continue reading other users even if one fails
                }
            }
        }
        
        return users;
    }
    
    /**
     * Saves all users to the database file.
     * 
     * @param users the list of users to save
     * @throws IOException if an error occurs while writing to the file
     */
    public void saveUsers(List<User> users) throws IOException {
        Path dbPath = Paths.get(DATABASE_FILE);
        
        try (BufferedWriter writer = Files.newBufferedWriter(dbPath)) {
            for (User user : users) {
                String line = formatUser(user);
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        }
    }
    
    /**
     * Adds a new user to the database.
     * 
     * @param user the user to add
     * @throws IOException if an error occurs while saving
     */
    public void addUser(User user) throws IOException {
        List<User> users = loadUsers();
        users.add(user);
        saveUsers(users);
    }
    
    /**
     * Updates an existing user in the database.
     * 
     * @param updatedUser the user with updated information
     * @throws IOException if an error occurs while saving
     */
    public void updateUser(User updatedUser) throws IOException {
        List<User> users = loadUsers();
        
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUUID().equals(updatedUser.getUUID())) {
                users.set(i, updatedUser);
                break;
            }
        }
        
        saveUsers(users);
    }
    
    /**
     * Finds a user by username.
     * 
     * @param username the username to search for
     * @return the User object if found, null otherwise
     * @throws IOException if an error occurs while reading
     */
    public User findUserByUsername(String username) throws IOException {
        List<User> users = loadUsers();
        
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        
        return null;
    }
    
    /**
     * Checks if a username is already taken.
     * 
     * @param username the username to check
     * @return true if username is taken, false otherwise
     * @throws IOException if an error occurs while reading
     */
    public boolean isUsernameTaken(String username) throws IOException {
        return findUserByUsername(username) != null;
    }
    
    /**
     * Checks if an email is already registered.
     * 
     * @param email the email to check
     * @return true if email is taken, false otherwise
     * @throws IOException if an error occurs while reading
     */
    public boolean isEmailTaken(String email) throws IOException {
        List<User> users = loadUsers();
        
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Parses a user from a database line.
     * 
     * @param line the database line to parse
     * @return the parsed User object
     */
    private User parseUser(String line) {
        String[] fields = line.split(Pattern.quote(FIELD_SEPARATOR));
        
        if (fields.length != 7) {
            throw new IllegalArgumentException("Invalid user format: expected 7 fields, got " + fields.length);
        }
        
        String username = fields[0];
        String passwordHash = fields[1];
        String email = fields[2];
        UUID uuid = UUID.fromString(fields[3]);
        boolean isAdmin = Boolean.parseBoolean(fields[4]);
        double accountBalance = Double.parseDouble(fields[5]);
        LocalDateTime registrationTime = LocalDateTime.parse(fields[6], DATE_FORMATTER);
        
        return new User(username, passwordHash, email, uuid, isAdmin, accountBalance, registrationTime);
    }
    
    /**
     * Formats a user for database storage.
     * 
     * @param user the user to format
     * @return the formatted string representation
     */
    private String formatUser(User user) {
        return user.getUsername() + FIELD_SEPARATOR +
               user.getPassword() + FIELD_SEPARATOR +
               user.getEmail() + FIELD_SEPARATOR +
               user.getUUID().toString() + FIELD_SEPARATOR +
               user.isAdmin() + FIELD_SEPARATOR +
               user.getAccountBalance() + FIELD_SEPARATOR +
               user.getRegistrationTime().format(DATE_FORMATTER);
    }
}

