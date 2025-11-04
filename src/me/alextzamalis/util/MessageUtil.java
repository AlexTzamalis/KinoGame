package me.alextzamalis.util;

import java.util.Date;

/**
 * Utility class for displaying messages to the console.
 * 
 * <p>This class centralizes all user-facing messages to ensure consistency
 * and make future localization easier. It provides methods for:
 * <ul>
 *   <li>Welcome and informational messages</li>
 *   <li>Input prompts</li>
 *   <li>Error and retry messages</li>
 *   <li>Success messages</li>
 * </ul>
 * 
 * @author AlexTzamalis
 * @version 1.0
 */
public class MessageUtil {

    private Date date = new Date();

    /**
     * Constructs a new MessageUtil instance.
     */
    public MessageUtil() {
        // Empty constructor
    }

    /**
     * Displays the current date and time.
     */
    public void currentDate() {
        System.out.println(this.date);
    }

    /**
     * Displays the welcome message for the application.
     */
    public void welcomeMesasge() {
        System.out.println("--- Welcome to Kino Game! ---");
        System.out.println();
    }

    /**
     * Prompts the user to choose between sign in or sign up.
     */
    public void signInsignUpMessage() {
        System.out.print("Sign In? or Sign up? (in/up) >> ");
    }

    /**
     * Prompts the user to enter their first name.
     */
    public void userFirstNameInput() {
        System.out.print("Enter your First Name: ");
    }

    /**
     * Displays a retry message for invalid first name input.
     */
    public void userFirstNameRetry() {
        System.out.print("You must have a name that has 3-20 characters >> ");
    }

    /**
     * Prompts the user to enter their last name.
     */
    public void userLastNameInput() {
        System.out.print("Enter your Last Name: ");
    }

    /**
     * Displays a retry message for invalid last name input.
     */
    public void userLastNameRetry() {
        System.out.print("You must have a last name that has 3-20 characters >> ");
    }

    /**
     * Prompts the user to enter their age.
     */
    public void userAgeInput() {
        System.out.print("Enter your age: ");
    }

    /**
     * Displays a retry message for invalid age input.
     */
    public void userAgeRetry() {
        System.out.print("You must be at least 21 to sign up!: ");
    }

    /**
     * Prompts the user to enter their email address.
     */
    public void userEmailInput() {
        System.out.print("Enter your Email: ");
    }

    /**
     * Displays a retry message for invalid email format.
     */
    public void userInvalidEmailExceptionRetry() {
        System.out.print("Please enter a valid Email: ");
    }

    /**
     * Displays a generic retry message for email input errors.
     */
    public void userEmailExceptionRetry() {
        System.out.print("Try again: ");
    }

    /**
     * Prompts the user to enter their password.
     */
    public void userPasswordInput() {
        System.out.print("Enter your Password: ");
    }

    /**
     * Displays a retry message for invalid password input.
     */
    public void userPasswordRetry() {
        System.out.print("You must have a password that has at least 4 or more digits: ");
    }

    /**
     * Displays a success message after successful user registration.
     */
    public void successfulSignUp() {
        System.out.println("You signed up successfully!");
    }

    /**
     * Displays a welcome message after successful login.
     * 
     * @param name the name of the user who logged in
     */
    public void successfulSignIn(String name) {
        System.out.println("Welcome back " + name);
    }
}
