package me.alextzamalis.scanner;

import me.alextzamalis.exceptions.InvalidEmailExcpetion;
import me.alextzamalis.file.FileWriters;
import me.alextzamalis.process.UserProcess;
import me.alextzamalis.util.Constants;
import me.alextzamalis.util.MessageUtil;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserInput{


    private UserProcess user;
    private Constants constants = new Constants();
    private final Scanner input = new Scanner(System.in);

    private String userCurrentInput;
    private String userCurrentEmail;
    private String userCurrentPassword;
    private int userCurrentAge;
    private boolean signInInput = false;

    public UserInput() {

    }

    // setter for wiring after creation
    public void setUserProcess(UserProcess user) {
        this.user = user;
    }

    /*
       Stores users current input and checks if the user wants to sign in or sign up.
     */
    public void userSignInSignUp() {
        userCurrentInput = input.nextLine();
        while(!userCurrentInput.equalsIgnoreCase("in") && !userCurrentInput.equalsIgnoreCase("up")) {
            System.out.print("Wrong input! you can only choose (in / up) >> ");
            userCurrentInput = input.nextLine();
        }
    }

    public boolean signInSignUpCheck() {
        return this.userCurrentInput.equalsIgnoreCase("in");
    }

    public void userFirstNameInput(MessageUtil messageUtil) {
        messageUtil.userFirstNameInput();
        userCurrentInput = input.nextLine();
        while(userCurrentInput.length() < constants.MIN_NAME_CHAR || userCurrentInput.length() > constants.MAX_NAME_CHAR) {
            messageUtil.userFirstNameRetry();
            userCurrentInput = input.nextLine();
        }
        user.setUserFirstName(userCurrentInput);

        userCurrentInput = null; // unassigns the current first name after it is set to the user and stored
    }

    public void userLastNameInput(MessageUtil messageUtil) {
        messageUtil.userLastNameInput();
        userCurrentInput = input.nextLine();
        while(userCurrentInput.length() < constants.MIN_NAME_CHAR || userCurrentInput.length() > constants.MAX_NAME_CHAR) {
            messageUtil.userLastNameRetry();
            userCurrentInput = input.nextLine();
        }
        if (user != null) {
            // example: user.setUserName(userCurrentInput);
        }
        user.setUserLastName(userCurrentInput);

        userCurrentInput = null; // unassigns the current last name after it is set to the user and stored
    }

    public void userAgeInput(MessageUtil messageUtil) {
        messageUtil.userAgeInput();
        userCurrentAge = input.nextInt();
        while(userCurrentAge < constants.MINIMUM_AGE_REQUIREMENT) {
            messageUtil.userAgeRetry();
            userCurrentAge = input.nextInt();
        }

        user.setUserAge(userCurrentAge);

        userCurrentAge = 0;
        input.nextLine();
    }

    public void userEmailInput(MessageUtil messageUtil) {
        messageUtil.userEmailInput();
        userCurrentEmail = input.nextLine();
        while(true) {
            try {
                if (isValidEmail(userCurrentEmail)) {
                    if (user != null) {
                        user.setUserEmail(userCurrentEmail);

                        userCurrentEmail = null; // unassigns the current email after it is set to the user and stored
                    }
                    break;      // exit loop once current email is valid
                } else {
                    throw new InvalidEmailExcpetion("Invalid email format!");
                }

            } catch (InvalidEmailExcpetion e){
                System.out.println(e.getMessage());
                messageUtil.userInvalidEmailExceptionRetry();
                userCurrentEmail = input.nextLine();

            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                messageUtil.userEmailExceptionRetry();
                userCurrentEmail = input.nextLine();
            }
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email != null && email.matches(emailRegex);
    }

    public void userPasswordInput(MessageUtil messageUtil) throws NoSuchAlgorithmException {
        messageUtil.userPasswordInput();
        userCurrentPassword = input.nextLine();
        while(userCurrentPassword.length() < constants.MIN_PASSWORD_DIGITS || userCurrentPassword.length() > constants.MAX_PASSWORD_DIGITS) {
            messageUtil.userPasswordRetry();
            userCurrentPassword = input.nextLine();
        }
        user.setUserPassword(userCurrentPassword);

        userCurrentPassword = null; // unassigns the current password after it is set to the user and stored

    }

    // close scanner on app exit if added on exit
    public void closeScanner() {
        input.close();
    }
}
