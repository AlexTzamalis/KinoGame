package me.alextzamalis.util;

/**
 * Centralized constants used throughout the application.
 * 
 * <p>This class contains all application-wide constants including:
 * <ul>
 *   <li>File paths for data storage</li>
 *   <li>Validation constraints (name length, password length, age requirements)</li>
 * </ul>
 * 
 * <p>All fields are static and final, making them accessible without instantiation
 * and preventing modification after initialization.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class Constants {

    /** File path for storing user credentials */
    public static final String USER_CREDENTIALS_FILE = "User_Credentials.txt";
    
    /** File path for storing user data */
    public static final String USER_DATA_FILE = "User_Data.txt";

    /** Minimum number of characters required for a name */
    public static final int MIN_NAME_CHAR = 3;
    
    /** Maximum number of characters allowed for a name */
    public static final int MAX_NAME_CHAR = 20;

    /** Minimum number of characters required for a password */
    public static final int MIN_PASSWORD_DIGITS = 4;
    
    /** Maximum number of characters allowed for a password */
    public static final int MAX_PASSWORD_DIGITS = 48;

    /** Minimum age requirement for user registration */
    public static final int MINIMUM_AGE_REQUIREMENT = 21;
    
    /**
     * Private constructor to prevent instantiation.
     * This class is meant to be used as a utility class with static constants only.
     */
    private Constants() {
        throw new UnsupportedOperationException("Constants class cannot be instantiated");
    }
}
