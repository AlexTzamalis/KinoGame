package me.alextzamalis.exceptions;

/**
 * Custom exception thrown when an invalid email format is detected.
 * This exception is used to enforce email validation throughout the application.
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class InvalidEmailException extends Exception {
    
    /**
     * Constructs a new InvalidEmailException with the specified detail message.
     * 
     * @param message the detail message explaining why the email is invalid
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}
