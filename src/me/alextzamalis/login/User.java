package me.alextzamalis.login;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a user in the system with their credentials and account information.
 * 
 * <p>This class encapsulates all user-related data including:
 * <ul>
 *   <li>Authentication credentials (username, password hash)</li>
 *   <li>Contact information (email)</li>
 *   <li>Account information (balance, admin status)</li>
 *   <li>Metadata (UUID, registration time)</li>
 * </ul>
 * 
 * <p><strong>Security Note:</strong> The password field stores a SHA-256 hash,
 * never the plain text password. This ensures that even if user data is compromised,
 * the original passwords cannot be recovered.
 * 
 * @author AlexTzamalis
 * @version 2.0
 */
public class User {

    private final String username;
    private String passwordHash;  // Stores hashed password, never plain text
    private final String email;
    private final UUID uuid;
    private final boolean isAdmin;
    private double accountBalance;
    private final LocalDateTime registrationTime;

    /**
     * Constructs a new User with the specified credentials.
     * 
     * <p><strong>Important:</strong> The password parameter should already be hashed
     * before being passed to this constructor. Never store plain text passwords.
     * 
     * @param username the user's unique username
     * @param passwordHash the SHA-256 hash of the user's password
     * @param email the user's email address
     * @param uuid the unique identifier for this user
     * @param isAdmin whether this user has admin privileges
     * @param accountBalance the initial account balance
     * @param registrationTime the date and time when the user registered
     */
    public User(String username,
                String passwordHash,
                String email,
                UUID uuid,
                boolean isAdmin,
                double accountBalance,
                LocalDateTime registrationTime) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (uuid == null) {
            throw new IllegalArgumentException("UUID cannot be null");
        }
        if (registrationTime == null) {
            throw new IllegalArgumentException("Registration time cannot be null");
        }
        
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.uuid = uuid;
        this.isAdmin = isAdmin;
        this.accountBalance = accountBalance;
        this.registrationTime = registrationTime;
    }

    /**
     * Returns the user's username.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the user's password hash.
     * 
     * <p><strong>Security Note:</strong> This returns the hashed password, not the
     * plain text password. Use this for comparison during authentication.
     * 
     * @return the SHA-256 hash of the password
     */
    public String getPassword() {
        return passwordHash;
    }

    /**
     * Returns the user's email address.
     * 
     * @return the email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the user's unique identifier (UUID).
     * 
     * @return the UUID
     */
    public UUID getUUID() {
        return uuid;
    }

    /**
     * Returns whether this user has admin privileges.
     * 
     * @return true if the user is an admin, false otherwise
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Returns the user's account balance.
     * 
     * @return the account balance
     */
    public double getAccountBalance() {
        return accountBalance;
    }

    /**
     * Sets the user's account balance.
     * 
     * @param accountBalance the new account balance
     * @throws IllegalArgumentException if balance is negative
     */
    public void setAccountBalance(double accountBalance) {
        if (accountBalance < 0) {
            throw new IllegalArgumentException("Account balance cannot be negative");
        }
        this.accountBalance = accountBalance;
    }

    /**
     * Adds funds to the user's account balance.
     * 
     * @param amount the amount to add
     * @throws IllegalArgumentException if amount is negative
     */
    public void addToBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        this.accountBalance += amount;
    }

    /**
     * Subtracts funds from the user's account balance.
     * 
     * @param amount the amount to subtract
     * @throws IllegalArgumentException if amount is negative or exceeds balance
     */
    public void subtractFromBalance(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount > this.accountBalance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        this.accountBalance -= amount;
    }

    /**
     * Returns the date and time when the user registered.
     * 
     * @return the registration time
     */
    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    /**
     * Updates the user's password hash.
     * 
     * <p><strong>Important:</strong> The password parameter should already be hashed
     * before being passed to this method. Never store plain text passwords.
     * 
     * @param passwordHash the new SHA-256 hash of the password
     * @throws IllegalArgumentException if passwordHash is null or empty
     */
    public void setPassword(String passwordHash) {
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalArgumentException("Password hash cannot be null or empty");
        }
        this.passwordHash = passwordHash;
    }
}
